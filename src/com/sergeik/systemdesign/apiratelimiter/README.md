# API Rate limiter

## Requirements

### FR
1. Limit the number of requests an entity can send to an API within a time window
2. The APIs are accessible through a cluster, so the rate limit should be
considered across different servers. User should get an error when he reaches 
the threshold.

### NFR
1. Highly available. It should always work since it protects servers
2. Should not introduce substantial latency.

## How to do limiting
Rate limiting is a process that is used to define the rate and speed at which consumers
cas access APIs.

Throttling is the process of controlling the usage of the APIs by customers during 
a given period.

## Types
1. Hard throttling - cannot exceed
2. Soft throttling - can exceed the limit by small percentage
3. Elastic/dynamic - can exceed if system resources allow

## Algorithms

1. Fixed limit. The window considered starts at fix point (beginning of a minute).
2. Rolling window. The window starts from the fraction of the time at which the 
request is made plus the time window length.

## High level design

```
clients<-------->web server<------->rate limiter
                                        |
                                        |
                                   ---------------
                                    |           |
                                    V           V
                            backend storage     cache server
``` 

## Basic design and algorithms

### Fixed window
If we go with fixed window we still can get too many requests. Consider scenario
when a user makes max call at the end of the period and right in the beginning.

Atomicity - in a distributed environment race condition may appear which will allow
more requests. Redis lock can help.

Memory
```
8 (UserID) + 2 (count) + 2 (epoch time if we store only minute and second) = 12 bytes
```

Hash table - `(12 (hash table overhead) + 20) bytes * 1M = 32MB`.

Although this can fit into one server we still should not use one machine. If we 
assume a rate limit of 10 requests/s, this would translate into 10M QPS! We can assume
Redis or Memcached for distributed storage.

### Sliding window
We can maintain a sliding window if we can keep track of each request per user. We
can store timestamp of each request in Redis Sorted Set in our value field of
hash table.

When a request comes (rate is 3 request/minutes):
1. Remove all the timestamps from the sorted set that are older than `cur time - 1 minute`.
2. Count the number elements in the set. Reject if count more than 3.
3. Insert the current time in the sorted set and accept the request.

#### Memory
Assume userID is 8 bytes. Each epocj time is 4 bytes. Suppose we need a rate limiting 
of 500 request/hour. Assume 20 bytes hash table overhead and 20 bytes for Sorted Set.
At max we'll need
```
8 + (4 + 20) * 500 + 20 = 12KB
``` 
To track 1M users at a time `12KB * 1M = 12GB`.

This approach needs much more memory than fixed window.

### Sliding window with counters

What if we keep track of request counts for each user using multiple fixed time
windows, e.g. 1/60th the sie of our rate limit's time window. If the rate limit
is hourly we can keep a count for each minute and calculate the sum of all counters 
in the past hour when we receive a new request. This would reduce memory usage.

We can store the counters in Redish Hash as it offers efficient storage for fewer than
100 keys. When each reques increments a counter in the hash, it also sets the hash
to expire an hour later. We will normalize each time to a minute.

#### Memory
```
8(userid) + (4(ts) + 2(counter) + 20(redis hash overhead)) * 60 
+ 20(hash table overhead) = 1.6KB
```

For 1M users `1.6KB * 1M = 1.6GB`

## Data sharding and caching

### IP
We throttle based on IP. It's not optimal in terms of differentiating between
good and bad actors.

### User
It'll be more fair, but what if we need to throttle before user is authenticated?
We'll have to disable throttling which will allow hackers to perform denial of
service attack by entering wrong credentials.

### Hybrid
A right approach could be to do both per-IP and per-User limiting, as they both have
weaknesses when implemented alone, though, this will result in more cache entries
with more details per entry, hence requiring more memory and storage.
