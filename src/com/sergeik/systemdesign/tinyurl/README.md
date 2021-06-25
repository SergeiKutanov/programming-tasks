# Design of service like TinyURL

## Requirements
### Functional requirements
1. Given a URL, the service should generate a shorter and unique version of it.
2. When the link is accessed the service should redirect to original URL.
3. Users shold be able to optionally pick a custom short link
4. Links will expire after a standard default timespan. Users should be able to set custom expiration
 time.
 
### Non-functional requirements
1. High availability
2. Redirection should happen in real time with minimal latency
3. Shortened links should not be guessable

### Extended requirements
1. Analytics, eg how many times a link is accessed
2 API access to the service

## Capacity, estimation and constraints
The system will be read-heavy. Assume 100:1 ration between read and write.

### Traffic estimates
Assuming we'll have 500M new short URLs per month. With the ratio 100:1 we expect 50B redirects. 
So in a month
```
100 (reads) * 500M (links) = 50B
```
Queries per second (QPS) for the system:
```
500M / (30 days * 24 hours * 3600 seconds) ~ 200 URL/second
```
Considering 100:1 ration, URLs redirects per second will be
```
100 * 200 URL/s = 20K/s
```

### Storage estimates
Assume we store each link for 5 years. Since we expect 500M URLs per month, the total number of
 objects:
```
500M * 5 years * 12 months = 30B
```
We'll assume each stored object is 500 bytes, we'll need 15TB of total storage:
```
30B * 500 bytes = 15TB
```
### Bandwidth estimates
For write requests, since we expect 200 new URLs every second, total incoming data for the service
will be 100Kb per second:
```
200 * 500 bytes ~ 100Kb/s
```
For read requests, since we expect ~20K urls redirects, total outgoing data will be 10MB/s
```
20K * 500 bytes ~ 10MB/s
```
### Memory estimates
If we want to cache some hot links, how much memory we'll need? If we follow 80/20 rule of URLs to
 generate 80% of traffic, we'd like to cache these 20% of hot URLs.
Since we have 20K requests per second, we'll be getting 1.7B requests/day:
```
20K * 3600 seconds * 24 hours ~ 1.7B
```
To cache 20% of these requests, we'll need 170GB of memory
```
0,2 * 1.7B * 500 bytes ~ 170GB
```
Note: there will be many duplicate requests (of the same URL), our actual memory usage well be less.
### High level estimates

| Property | Estimate |
|----------|-------|
| New URLs | 200/s |
| URL redirects | 20 K/s |
| Incoming data | 100kb/s |
| Outgoing data | 10MB/s |
| Storage for 5 ears | 15TB |
| Memory for cache | 170Gb |

## System API
We can have SOAP or REST APIs to expose the functionality of our service.
```
createURL(api_dev_key, original_url,custom_alias=None, user_name=None, expire_date=None): String
```
On success return the shortened URL, otherwise error code

```
delete(api_dev_key, url_key)
```
On success returns "URL Removed", otherwise error code.

#### Abuse prevention
Each API user can be limited to a certain number of URLs per time period.

### Database design
1. We'll store billions of records
2. Each object is small (<1K)
3. No relations between records
4. Service is read-heavy

#### Database schema
| |Table URL|
|---|---------|
|PK|Hash: varchar(16)|
| |OriginalURL: varchar(512)|
| |CreationDate: datetime|
| |ExpirationDate: datetime|
| |UserId: int|

| |Table User|
|---|---------|
|PK|UserId: int|
| |Name: varchar(20)|
| |Email: varchar(32)|
| |CreationDate: datetime|
| |LastLogin: datetime|

Since we'll be storing billions of records and we don't need relations a NoSQL db is a good candidate,
like DynamoDB, Cassandra. A NoSQL choice will also be easier to scale.

## Basic system design and algorithms
### Encoding actual URL
We can compute a uniqe hash (MD5 or SHA256) of the given URL. We can use base64 encoding to cover all
characters. What should be the length of the key? 6,8,10?

Using base64 encoding, a 6 letters long key would result in 64^6 ~ 68.7B possible string.
8 letters - 64^8 ~ 281 trillion strings.
Let's assume 6 characters is enough.
If we use MD5 as hash algo, it will produce a 128 bit hash value. After base64 encoding we'll get
a string with more than 21 characters (since each base64 character encodes 6 bits of the hash).
Since we have 6 letter positions, we could take only first 6 characters after encoding but this can
result in duplicate keys. To avoid it we can choose some other characters out of the encoding or swap 
some characters.
#### Issues
1. If multiple users enter the same URL they will get the same short URL - not acceptable
2. What if parts of URL are URL-encoded? then same location URLs will produce different keys
#### Workaround issues
We can append an increasing sequence number to each input URL to make a unique. Problem - 
ever-increasing number which can overflow. Also impacts performance.

Another approach is to add user id (unique) to the input URL. But if user is not signed in, we'd have
to ask the user to choose a unique key. Even with this we'll need to keep asking for a key until
we get a unique one.

```
        ---shorten a URL ---------->        ----encode URL------------>
Client                              Server                              Encoding
        <---return shortened URL----        --append seq and encode--->     /
                   \    \                                                  /
            success \    \failed due to duplicate                         /
                     \    \                                              /
                     -----------Database---------------------------------
``` 

### Generating keys offline
We can have a standalone Key Generation Service (KGS) that generates random six-letter string and
stores them in DB beforehand. We'll get an available key when needed. This is much simpler. No need to
encode actual URL, worry about duplicates and collisions.

#### Concurrency
As soon as key is usd it should be marked so. If multipe servers read the key at the same time.
We can use two tables in KGS. For available and used keys. Once a key is given it'll be moved to used
table. Also some keys can be kept in memory to speed up the process. Once a key is loaded in memory it
can be moved to `used` table. If KGS dies we'll loose some keys since they are in `used` but this is
acceptable. KGS has to make sure not to give same key to multiple servers. For that it must 
synchronize (or lock) on data structure holding the keys.

**Key DB size** with 64 base encoding we can generate 68.7B unique six letters keys.
```
6 (chars per key) * 68.7B = 412GB
``` 
Since KGS will become a single point of failure we can have a standby replica of KGS.

**Key lookup** in our database to get the full URL. If found return 302 with the new location, 
otherwise 404.

**Custom alias size**. We should impose a limit on the alias to avoid overuse.

## Data partitioning and replication
### Range based
We can store URLs in a separate partitions based on the hash key's first letter. We can combine less
frequently used letters into one partition. The problem is it can lead to unbalanced DB servers.

### Hash based
In this scheme, we take a hash of the object we are storing. Calculate which partition to use based
on the hash. We can take the hash of the 6 chars key. The hash function will randomly distribute
URLs into different partitions (map any key to a number [1...256]). This still can lead to overloaded
partitions, which can be solved using Consistent hashing (//TODO lookup).

## Cache
We should cache frequently used URLs. Memcached will do the job.

### Memory
If we cache 20% of daily traffic, we need 20% of 170GB. Since 256GB if memory is common in modern
day servers we can fit all cache into one server. Or we can use few smaller server to store all these
hot URLs.
### Cache policy
Least recently used records should get evicted from the cache.

To further increase the efficiency, we can replicate out caching servers to distribute the load
between them.

### Cache replica update
Whenever a cache miss happens we can update the cache with the value from DB app pass the new entry
to all cache replicas.
```
            --access a short url---->       ---find url--------------------->
    client                           server <-----url found------------------   Cache
    |       <--return error---------    |   <-----url not found--------------       ^
    |-----------------------            |------------------------------------       |
            ^           ^                            ^      ^       |               |update
            |no,        |yes,                        |URL   |URL    |Find           |cache
            |re         |return                      |found |not    |original       |
            |di         |401                         |      |found  |URL            |
            |rect       |                            |      |       |               |
            |           |                            |      |       |               |
            |           |                            |      |       V               |
            -----------------------------           -------------------------------------
                    Has url expired                         Database
                    or user does not have
                    permissions
```

## Load balancer
Three locations
1. Between clients and application server
2. Between application servers and DB servers
3. Between application server and Cache servers
Round robin approach doesn't consider server load, as a result overloaded server is give latency.
To avoid this a more intelligent approach can be taken. Ping server periodically to get their load
and adjust traffic based on it.

## Purging or DB cleanup
We can do puging periodically but not too often. It might allow expired links to stick around a 
little longer but those links won't be returned to user
1. Once an expired link is access we can delete it
2. A separate service can run clean up. It should be lightweighted and run only when expected 
traffic is low.
3. We can have default expiration time
4. After the link is deleted key can be reused

## Telemetry
How many times a short URL has been used, what were user locations, etc? How will we store those?
If it's part of DB row that gets updated on each view, wha will happen when a popular URL is
slammed with large number of concurrent requests?

Some stats worth tracking: country of the visitor, data and time of access, referrer browser or 
platform.

## Security and permissions
Can users create private URLs or allow a particular set of user to access a URL?

We can store permissions level with each URL in database. We can also create a separate table to
store UserIDs that have permission to see a specific URL. Given we're storing our data in a NoSQL 
wide-column DB like Cassandra, the key for the table storing permissions would be the `Hash` (or 
the KGS generated key). The columns will store the UserIds of those that have permission to see
the URL.  