# Facebook messenger system design
## Requirements and goals
### Functional requirements
1. Messenger should support 1:1 conversation between users
2. Messenger should keep track on users offline/online
3. Messenger should support persistent storage of char history
### Non-functional requirements
1. Users should have real time experience
2. System should be highly consistent
3. Messenger's high availability is desirable, but we can tolerate lower availability
in the interest of consistency.
### Extended requirements
1. Group chats
2. Push notifications
## Capacity estimation and constraints
Assume we have 500M active users, each user sends 40 messages per day, this gives us 20B
messages per day
**Storage:** Assume message size on average is 100 bytes, so to store all the messages 
for one day we'll need 2TB of storage.
```
20B * 100 bytes = 2TB
```
To store 5 years:
```
2TB * 365 * 5 = 3.6PB
```
Other than messages we need to store users' information, messages' metadata. The above 
calculation doesn;t consider data compression and replication.

**Bandwidth estimation:** 
```
2TB / 86400 sec ~ 25MB/s
```
Since each incoming message goes out to another user we need the same amount for 
outgoing flow.

**High level estimates:**
1. Total messages: 20B/day
2. Storage: 2TG/day
3. Storage total: 3.6PB/5 years
4. Incoming data: 25MB/s
5. Outgoing data: 25MB/s

## High level design
We'll need a chat server which will be the central piece. When a user wnats to send
a message, he connects to the server and the server sends the message to another user
and also sends it to the store in database.

**Detailed workflow:**
1. User A sends a message to user B through the chat server
2. The server receives the message and sends to an acknowledgment to user A
3. The server stores the message in DB and sends it to user B
4. User b receives the message and send the acknowledgment to the server
5. The server notifies user A that the message has been sent

## Detailed component design
To start assume everything runs on one server.

**Use cases**
1. Receive incoming messages and deliver outgoing ones
2. Store and retrieve messages from DB
3. Keep record of which user is online/offline and notify relevant users.

### Messages handling
How would we efficiently send/receive messages? To send messages, a user needs to 
connect to the server and post messages. To get a message there are two options:
1. Pull model - users can periodically ask the server
2. Push model - users can keep a connection open with the server and can depend upon 
the server notifying them of new messages.

If we go with the first approach, the the server needs to keep track which messages
are stil waiting to be delivered, and as soon as the receiving user connects to the
server, the server can return pending messages. To minimize latency client will have
to make requests quite often and most of them will be empty. This is a waste of network.
 With the second approach server won't need to keep track of pending messages and will
 push them to the client right after they are received.
 
 #### How will clients maintain the connection?
 We can user Long Polling or Web Sockets. Long polling will have an open connection
 with the server and will wait until a new message comes. After it's received a new
 connection can be opened. If connection times out client can re-open it.
 
 #### How will server maintain connections map
 It can use a HashMap to store userId -> connection. On incoming message it would check
 if there's an open connection and will send the message.
 
 #### What will happen if user is offline?
 If the receiver is disconnected we can notify the sender first. Then if the long 
 polling request timed out we can expect the connection to restore soon and send the
 message. This retry can be embedded into client's logic or server can keep the 
 message for some time and retry.
 
 #### How many chat servers do we need?
 Let's plan for 500M connections at any time. A modern server can handle 50K concurrent
 connections at any time, we would need 10K servers.
 
 #### How would we know which server holds the connection with a user?
 We can introduce a software load balancer in front of char server. This can help map
 each UserID to a server.
 
 #### How should a server process a deliver message request?
 It needs to:
 1. Store the message in DB
 2. Send the message to recevier
 3. Send an acknowledgement to the sender
 
 The char server will first find the server that hold the connection with the user 
 and pass the message to it. Then it can send the acknowledgement to the sender. It
 doesn't have to wait for the message to be stored in DB, it can be done after.
 
 #### How does the messanger maintain the sequence of messages?
 We can store timestamps whne message was sent but:
 1. User 1 sends a message M1 to the server for User 2
 2. Server receives M1 at T1
 3. Meanwhile, user 2 sends M2 to U1
 4. The server receives M2 at T2, such that T2 > T1
 5. The server sends the message M1 to U2 and M2 to U1
 
 So user 1 will see M1 and then M2. User 2 will see M2 and then M1.
 
 To resolve this we need to have a sequnce number for each message for each user. This
 number will determin the ordering of messages. With this solutions both client will
 see a different view but it will be consistent for each of them on all devices.
 
 ### Storing and retrieving messages from the DB
 Two options:
 1. Start a separate thread which will work with DB
 2. Send async request to DB
 
 We have to keep in mind:
 1. How to efficiently work with DB connection pool
 2. How to retry failed request
 3. Where to log those requests that failed
 4. How to retry these logged requests when issues are resolved
 
 #### Which storage system to use?
 We need to have a database which supports a very high rate of small updates and also
 fetch a range of records quickly.
 
 We can't user RDBMS or NoSQL because we can't afford to read/write a row each time
 a message is sent. This will add latency and load to DB.
 Both of our requirements can be met with a wide-column database solution like HBase.
 It's a column oriented ket-value NoSQL database that can store multiple values against
 one ley into multiple columns. This way of storage not only helps storing a lot of 
 small data quickly, but also fetching rows by the key or scanning ranges of rows.
 It's also an efficient database to store variably sized data, which is also required.
 
 #### How should clients fetch messages?
 They can paginate the messages, page size should be different for mobile and desktop 
 devices.
 
 ### Managing user's status
 Since we're keeping a map from user id to connection we can query it to get the list
 of users online. With 500M users at any time, if we have to broadcast each status 
 change to all relevant users, it will consume a lot of resources. We can do 
 optimization:
 1. When client start it can pull status of all relevant users.
 2. When a user sends a message to user that has gone offline we can send a failure
 to the sender and update the status
 3. When a user comes online, the server can always broadcast the status with a little
 delay in case the user goes offline quickly
 4. Client can pull the status of users visible in viewport at the moment only.
 5. When a client starts a new chat we can pull the status as well
 
 ```
User A-|
User B-|---------->Load Balancer(User to Chat server)
User C-|                    |
                            V
                        Chat servers ----->Load Balancer(user to cache server mapping)
                            |               |
                            V               V
                        DB Shards-----> Caches
``` 
#### Summary
Clients will open a connection to the chat server to send a message. The server will
then pass it to the requested user. All the active users will keep a connection open 
with the server to receive messages. When a message arrives, the chat server will push
it to the receiving user on the long poll request. Messages can be stored in HBase, 
which supports quick small updates, and range based searches. The servers can broadcast
the online status of a user to other relevant users. Clients can pull status updates
for users who are visible in client's viewport on a less frequent basis.

## Data partitioning
### Based on UserID
We can keep all messages for a user in the same databse. If one DB shard is 4TB, we will
have `3.6PB/4TB~900`. Let's assume 1k for simplicity. So we'll find the shard id by
`userID % 1000`. It's quick.

In the beginning we can have multiple DB servers on one physical server. This logical
mapping must be understood by our hashing function. Once the stored data grows enough
we can easily move the DBs to servers of their own.

### Based on Message ID
This approach is not feasible since we'll have to fetch messages from different DBs
and this will introduce critical latency.

## Cache
We can cache few recent messages (~15) in a few recent conversations that are visible 
in user's chat viewport. Since we decided to store all user's messages in one shard
the cache should reside in one server too.

## Load balancing
We'll need a load balancer in from of our char servers, that can map each UserID to a
server that holds the connection. Similarly we'll need a load balancer for cache 
servers.

## Fault tolerance and replication
If a server fails we can have client to reconnect to an available server. It's extremly
hard to failover TCP connection to another server.

We can't have one copy of the user's data. For this either we have to store multiple
copies of the data on different servers or use technique like Reed-Solomon encoding
to distribute and replicate it.

## Extended requirements

### Group chat

We can have a separate group-chat object in out system that can be stored on the chat
server. A group chat can maintain list of users. Our LB can direct each group chat
message based on GroudChatID and the server handling that group chat can iterate
through the users of the chat to find servers which hold the connection with them
to deliver the message.

In databse we can store all the group chats in a separate table partitioned based
on GroupChatID.

### Push notification

In our current design we can send messages only to online users. Push notifications
will allow delivering messages to offline users.

For push nitifications users can opt in to receive them. Each manufacturer maintains
a set of servers that handles pushing notifications to the user.

We'll have to setup notification server, which will take the messages for offline users
and send them to the manufacturer's push notification server, which will send them
to the user's device.