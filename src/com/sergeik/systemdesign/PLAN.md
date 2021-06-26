# System design common plan

## Requirement sclarification
### Functional requirements
1. Create a list of what system should do
### Non-functional requirements

Some of `ities` can take a hit in favor of others. 

1. Concurrency
2. ACIDity
3. Consistency
4. Availability
5. Acceptable latency

## Capacity estimation and constraints
Get a starting point of what needs to be measured. For example, 
1. number of expected users,
2. types of data to be sent 
3. calculate data size and network throughput
4. Calculate storage needed per day
5. Calculate storage needed per lifespan (5 years)

Try identify if system is read, write or both heavy.

## Data model
Describe high level view of data model. DB tables and their relationships. Take into 
consideration scalability, read/write heavy system.

## Define system interface
If applicable define API interface

## High level design
Draw a high-level system diagram with 5-6 boxes representing main components of the
system. Try to identify what type of storage we'll need, RDBMS or NoSQL. If that's an
object storage like S3, think of how to store metadata for objects.

## Detailed design
Dive deeper into 2-3 components which require more attention. Present different 
approaches with pros and cons while keeping contraints in mind.
* Since we'll be storing massive amounts of data think of how to partition it.
    * Functional partitioning - divide DB into functional parts (used by a component)
    * Partition based on a field from data model. Can be userId, messageId, photoId
    * We can start with logical partitions (multiple DB servers on one server, easy to
    move after).
* How much and at which level should we introduce cache, think about what cache policy
to use (LRU|LFU). Try 80/20 rule, 20% of data generates 80% of traffic.
* Think about where to put load balancers and which approach to use:
    * Round robin - selects a live server randomly
    * Consider server load
    
## Discuss bottlenecks
1. Is there any single point of failure? If yes, how should we avoid it?
    1. Server instances
    2. Fallback server instance (in case we need only one server running, the fallback 
    sits idle until the first one fails)
2. Do we have enough data replicas to get right consistency level
3. How are we monitoring performance of our system, send notifications/alerts in case
of a failure.