# Dropbox system design
## Requirements and goals
1. Users can upload/download files from any device
2. Users can share files/folders with others
3. Synchronization between devices
4. Can store large files, up to a GB
5. ACID-ity is required (atomicity, consistency, isolation, durability) for all operations
6. Supports offline editing

## Design considerations
1. We should expect huge read and write volumes
2. Read:write ratio is the same
3. Internally, files should be stored in small chunks. This will help in managing big files. Only 
failed chunks will need to be reuploaded
4. We can reduce the amount of data exchange by transferring only updated chunks
5. By removing duplicated chunks we can save on storage
6. Keeping a local copy of metadata with the client can save a lot of round trips to the server
7. For small changes, clients can upload diffs instead of whole chunks.

## Capacity Estimation and Constraints
1. Assume we have 500M users and 100M daily active users
2. On average each connects from three different devices
3. On average if a user has 200 files, we will have 100B total files
4. Assume average files size is 100KB, this will give us 10 Petabytes of total storage
5. Assume we have one million active connections per minutes

## High level design
The user will specify a folder as workspace on their device. Any file in this folder will be synced 
with cloud and other devices.

At a high level we need to store files and their metadata and who this file is shared with. So we need:
1. Servers to upload files to the cloud storage (Block server)
2. Servers to update metadata
3. Mechanism to notify client to synch data
```
          ------------------------->Block Server    ----------------->  Cloud Storage
          |                                                  |
          |                                                  V
      Clients ------------------->  Metadata Server ------------------> Metadata storage
          ^                             |
          |                             |      
          |                             V
          |------------------------- synch server
```

## Component design
### Client
Monitors workspace and syncs file with cloud storage. Interacts with sync service to update metadata 
updates.
1. Upload and download files
2. Detect file changes in the workspace
3. Handle conflict due to offline concurrent updates

#### How to transfer files efficiently
Split into chunks of 4MB. We can calculate optimal chunk size from
1. Storage devices to optimize space utilization and IO operations per second
2. Network bandwidth
3. Average file size in the storage.
In metadata we should keep a record of each file and chunks.

We should keep metadat with client to avoid redundant server trips.

#### How clients efficiently listen to changes
We can periodically check for updates. This will not give us real time updates, produce unnecessary 
bandwidth. Pulling information in this manner is not scalable.

We can use HTTP long polling. The client requests information from the server with the expectation
that the server may not respond immediately. If the server had no new data for the client when the poll
is received, instead of sending an empty response it will hold the request open and wait for response
information to become available. Once it does have the new information, the server immediately sends 
an HTTP/S response to the client, completing the open HTTP/S request. Upon receipt of the server 
response, the client can immediately issue another server request for future updates.

We can divide clint into parts:
1. **Internal metadata database** will keep track of all files, chnks, their versions and their 
location in the file system
2. **Chunker** will split the files into smaller pieces. It will also be responsible for 
reconstructing a file from chunks. The algo will detect the parts that have been modified by the user
and only transfer those parts.
3. **Watcher** will monitor the local workspace and notify indexer if any action performed by the user.
Also listens to changes happening on other clients that are broadcasted by Sync service.
4. **Indexer** will process events received from the Watcher and update the internal metadata
database with info about the chunks of the modified files. Once chuns are processed 
(uploaded/downloaded), the Indexer will communicate with the Sync service to broadcast changes.

**How should clients handle slow servers?** Clients should exponentially back-off if the server
is busy. (Attempts retried, delay increased).

Mobile client should sync on demand.

### Metadata database
Responsible for maintaining versioning and meta information about files, users, workspaces. The
database can be a relational or NoSQL. Regardless of the type, the Sync service should be able to provide a 
consistent view of the files. Since NoSQL data stores do not support ACID properties in favor of scaleability we'll 
need to incorporate the ACID support programmatically. However using a relational DB can simplify the implementation
of the Sync service.
Should store info about:
1. Chunks
2. Files
3. User
4. Devices
5. Workspaces

### Sync service
Processes file updates and applies them. Syncs local meta database with the remote one. This is the most important 
piece. Desktop clients communicate with it to obtain updates or send files. If a client was offline it connects with
the service once it becomes online, checks meta db for consitency and proceeds with the updates.

It should be designed to transmit less data to achieve better response time. It can employ a differencing algo to 
reduce the amount of data trasferred. We should transmit only the differences. Server and clients can calculate
a hash (SHA-256) to see whether to update the local copy of a chunk or not. On the server if we have a chunk with the
same hash (even from a different user), we don't need to create another copy.

A messaging middleware should provide scalable message qeuing and change notifications to support a high number of
clients using pull or push strategies. So multiple Sync services instances can receive requests from a global request 
queue and the communication middleware will be able to balance the load.

### Message queue service
Supports async and loosely coupled message-based communication between system components. Should be able to 
efficiently store any number of messages in a highly available, reliable and scalable queue.

Two types of queues. 
1. Request global queue shared with all clients. Clients requests to update metadata will be sent 
here. From there Sync service will take it to update the metadata.
2. Response queues correspond to individual subscribed clients are responsible for delivering the update messages to
each client, we need to create separate Response Queues for each subscribed client to share update messages.
```
        ----------------------- Request queue------------------------------------
        |                                                                       |
client1-|<----------------------Response queue<---------------------|           |
        |                                                           |           V
client2-|<----------------------Response queue<---------------------|-------Sync service
        |                                                           |           |  
client3-|<----------------------Response queue<---------------------|           |
                                                                                V
                                                                            Meatada DB
```
### Cloud/Block storage
Stores chunks of files. Clients directly interact with it to send/receive objects. Separation from storage enables
us to use any astorage either in the cloud or in-house.

```
                Notification server<---sync queue----------Metadata servers<-------------------Metadata DB
                        |                                       ^                    ^              |
                        |                                       |                    |              |
client1-------->|       V                                       |                    |              V
client2-------->|--Network--------------------------------->Load Balancers           -----------Metadata Cache Server
client3-------->|       |                                        ^
                        |                                        |
                        |                                        |                 -------------Storage cache server
                        |                                        |                 |                    ^
                        |                                        |                 |                    |
                        |                                        |                 V                    |
                        ----------------------------------->Block Server----------------------->Block/Cloud storage
```

## File processing workflow
1. Client A uploads chunks to cloud storage
2. Client A updates metadata and commits changes
3. Client A gets confirmation and notifications are sent to Clients B and C about the changes
4. Client B and C receive metadata changes and download updated chunks

## Data deduplication
Technique for eliminating duplicate copies of data.
### Post process deduplication
First a chunk is uploaded, then by other service it's analyzed and deletes it if it's a duplicate.

Benefits:
1. Client doesn't wait for hash calculation or lookup

Drawbacks:
1. Unnecessarily store the data
2. Unnecessarily transferring data

### In-line deduplication
The copy lookup will be done in real-time and only a reference to the existing chunk will be added in the metadata.
This approach will give us optimal network and storage usage.

## Metadata partitioning
### Vertical
We can partition our database in a way that store tables related to one particular feature on one server. For example,
we can store all the user related tables in one database and all files/chunks related tables in another database.

Issues:
1. We'll still have scaling issues. If we have trillions of chunks, how would we further partition the table?
2. Joining two table in two separate databases can cause performance and consistency issues.

### Range based
Store files based on the first letter of the file path. Leads to unbalanced servers.

### Hash-Based
We take a hash of object and store it based on the value. In our case we can take the hashof FileID and determine
the partition. Hashing function will randomely distribute objects into different partitions. It'll yield a number
in [1...256] and it can be partition id. It can still lead to overloaded partitions, which can be solved by using
Consistent Hashing.

## Caching
We can have tow kinds of caches. To deal with hot files - cache for block storage. With a high-end commercial server
can have 144GB of memory. One such server can cache 36K chunks.

Least recently used policy can be a reasonable approach.

## Load balancer
1. Between clients and block servers
2. Between clients and metadata servers

Round robin will work initially. If a server is dead, LB will take it out of the rotation. But the problem is RR won't
take server load in consideration. If needed we can come up with a more sophisticate policy.

## Security, permissions and file sharing
We'll be storing the permissions of each file in out metadata DB to reflect what files are visible or modifiable by 
any user.