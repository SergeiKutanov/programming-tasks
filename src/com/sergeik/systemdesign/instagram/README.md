# Instagram design

## Requirements and goals
### Functional requirements
1. User should be able to upload/download/view photos
2. Users can perform searches on photo/video titles
3. Users can follow other users
4. The system should generate news feed consisting 
of top photos of the users he follows

### Non-functional requirements
1. Service needs to be highly available
2. The acceptable latency of the system is 200ms for news generation
3. Consistency should take a hit (in the interest of availability) if a user doesn't see 
a photo for a while; it should be ok
4. The system should be highly reliable; any uploaded photo or video should never be lost

### Out of scope
1. Adding tags
2. Search on tags
3. Commenting on photos
4. Tagging users on photos

## Considerations
The system will be read heavy, so we'll focus on building a system that can retrieve
images quickly.
1. Users can upload as many photos as they like, so efficient management of storage is 
crucial.
2. Low latency is expected while browsing photos.
3. Data should be 100% reliable, no photo/video should ever be lost.

## Capacity estimation and constraints
1. Assume we have 500M users with 1M active daily users
2. 2M new photos every day, that's 2M / 86400 = 23 photos per second
3. Average photo size 200kb
4. Total space required for 1 day of photos = 200kb * 2M = 400GB
5. Total space requireed for 10 years = 400GB * 365 * 10 ~ 1425TB

## High level system design
At high-level we need to support to scenarios:
1. Upload photos
2. View/search photos.
We'll an object storage server to store the photos and some database servers to store
meta data about the photos.

```
Client uploads image    \                                   /   image storage  
                         ------>Image hosting system<-------                   
Client views photo      /                                   \   image metadata
```
## Database schema
We need to store data about users, uploaded photos and people they follow

```
Photo
------
PK: PhotoId: int
UserId: int
PhotoPath: varchar(256)
PhotoLatitude: int
PhotoLongitued: int
UserLatitude: int
UserLongitude: int
CreationDate: datetime
```
```
User
----
PK UserId:int
Name: varchar(20)
Email: varchar(32)
DateOfBirth: datetime
CreationDate: datetime
LastLogin: datetime
```
```
UserFollow
-----------
PK UserId1:int
UserId2:int
```
The above schema can be stored in RDBMS like MySQL since we require joins.
The photos can be stored in a distributed file storage like S3.

We can also consider using a NoSQL DB to store the above schema. In this case all 
the info related to photo meta data can go to a table where the key would be the PhotoID
and the value will be a document containing the meta data. We'll also need an additional
table to store relationship between photos and users. We'll also need to store list of
followers. For both of the tables we can go with a wide-column data storage like Cassandra.

Cassandra or key-value stores, in general, always maintain a certain number of replicas
to offer reliability. Also, in such data stores, deletes don’t get applied instantly;
data is retained for certain days (to support undeleting) before getting removed from
the system permanently.

## Data size estimation
### User
Assuming each int and datetime are four bytes, each row in user table will be 
68 bytes:
```
User
----
PK UserId:int               4 bytes
Name: varchar(20)           20 bytes
Email: varchar(32)          32 bytes
DateOfBirth: datetime       4 bytes
CreationDate: datetime      4 bytes
LastLogin: datetime         4 bytes
-----------------------------------
                            68 bytes
```
500M Users = 68 * 5M ~= 32GB

### Photo
Each row will be 284 bytes
```
Photo
------
PK: PhotoId: int            4
UserId: int                 4
PhotoPath: varchar(256)     256
PhotoLatitude: int          4
PhotoLongitued: int         4
UserLatitude: int           4
UserLongitude: int          4
CreationDate: datetime      4
-------------------------------
                            284
```
If 2M photos are uplaoded every day, that's 0.5GB. For 10 years it's 
approximately 1.88TB.

### UserFollow
Each record 8 bytes.
500M users * 500M followers ~= 1.82TB

Total space for all tables for 10 years:
32GB + 1.88TB + 1.82TB = 3.7TB

## Component design
Photo uploads are slow as they are stored on a disk, reads are fast expecially if 
cache used.

Uploading can consume all available connections as it's a slow process. This means 
that reads can't be served if all connecitons are busy with uploads. Web server has
connection limit, let's say 500, so a server can't have > 500 concurrent reads/writes.
To avoid this bottleneck we can user different services for upload and read and scale
the services appropriatelly.
```
Client uploads \    
                -----> upload image request-------->Image storage
                                            \     /
                                             \  / 
                -----> download image request------>Image metadata
Client downloads/
```

## Reliability and redundancy
Loosing a file is not an option, so we'll need multiple copies of files as backup
in case a server dies. Same applies to all services in the system. To keep service 
available we'll need multiple copies of it. Redundancy removes single point of failure
from the system.

If only one service instance is required we can still run the second one which won't be
service any traffic but will kick in in case the first one fails.
```
Client uploads \    
                -----> upload image service-------->Image storage--->image replica
                -----> upload image service------/
                                            \     /
                                             \  / 
                -----> download image service------>Image metadata--->metadata replica
                -----> download image service----/
Client downloads/
```

## Data sharding
### Partitioning base on userId
Assume the partitioning is based on userID so we can keep all photos of one user on
one shard. If one DB shard is 1TB, we'll need four shards to store 3.7TB. Let's
assume for better performance and scaleability will have 10TB.

So we'll find shard number by UserId % 10 and then store the data there. To uniquely
identify a photo we can add a shard id to photo id.

Photo Ids can be generated on each shard with an increment id generator. Since we'll
be adding shard id to the photo key it'll make it unique.
#### Issues
1. Hot users? Several people follow same users
2. Some users will have more photos than others
3. What if we can't store photos of one user on one shard, this will produce latency if
we spread the photos between multiple shards
4. Storing all photos of one users can cause issues like unavailability. If one shard
goes down or higher latency if the shard is loaded

### Partitioning by photo id
If we can generate photo id and then find shard with `PhotoId % 10` the above problems 
would be solved. We wouldn't need to append shard id as the ids will be unique
throughout the system.

The IDs will have to be generated before putting an image in a shard. We could use
a separate single database to generate the ID. We can define a table to store photo id
and return it. But this will create a single point of failure. To avoid it we can use 
two DBs - one for odd IDs, one for even.

Script to generate such tables
```
KeyGeneratingServer1:
auto-increment-increment = 2
auto-increment-offset = 1

KeyGeneratingServer2:
auto-increment-increment = 2
auto-increment-offset = 2
```
We can also put a load balancer with round robin decision making. This still might get 
the DBs out of sync (one will generate more records than the other), but it won't
create issues.

### Future growth
We can have a large number of logical partitions to accomodate future data growth. In 
the beginning multiple logical partitions can reside on one server. Since each server
can have multiple instances of DB server we can have separate DB instance for each 
partition. Whenver we feel like we have too much data on one server we can migrate
some logical partitions to another server. We can have a config file to map logical 
partition to DB, so moving a partition to new server will just need updating
the config file. 

## Ranking and news feed generation
To create a news feed we'll need to pull the latest, popular and relevant photos of
a followed user.

For simplicity let's assume we fetch 100 photos into user's feed. Our application 
server will pull metadata for 100 latest photos of each followed user. Then it will 
submit the list to ranking algorithm which will determine the 100 top photos. A 
possbile problem is latency since we're pulling 100 photos from each user but we use
only 100 in total. To improve the efficiency we can pre-generate the news feed and 
store it in a separate table.

#### Pre-generating feeds
We can have a separate service to pre-generate the feeds and store them in a dedicated 
table. So whenver user needs to get a feed we'll just pull it form the table.

#### Approaches to send news feed contents to the user
1. Pull - clients can pull the feed from the server at a regualr interval or manually.
Problems: data might not be shown to the user until clients issue a pull request. Most
of the time the response will be empty since no new photos are available.
2. Push - servers can push the new data once it's available. To efficiently handle it
clients will have to maintain Long Poll request with the server to receive updates.
Problem - popular users, the server will have to push updates too frequently.
3. Hybrid - we can move popular users to a pull-based model. Use push for users with
small number of followers. Another approach is to push updates and a set frequency
and allow users to pull the feed more often manually if necessary.

## News feed creation with Sharded data
One of the requirements is to get a list of photos sorted by creation time. To achieve
this we can make creation date part of PhotoId, since it's used as primary key it will
quite quick to find the latest photos.

We can use epoch time for this. First part of a key will be the epoch time the second
is the incremented ID.
How will this affect photo table size:
```
    86400 sec/day * 365 * 10 years = 1.6 billion records.
``` 
We would need 31 bit to stor ethis number. Since on average we expect 23 photos per
 second we can allocate additional 9 bits to store the incremented id part. So every
 second we can store 2^9 = 512 new photos.
 
## Cache and load balancing
Out service should push the contents as close geographicaly to the user as possible 
using a large number of geo distributed photo cache servers and use CDNs.

We can introduce a cache for metadata servers to cache hot database rows. We can use
memcache tp cache the data. Application servers will hit the cache first before
proceeding to the database. Least recently used (LRU) cache policy will be a good 
candidate. Under this policy we'll be discard to ;east recently used records.
 