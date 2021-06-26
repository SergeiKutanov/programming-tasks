# Twitter system design

## Requirements

### FR
1. Users can post tweets
2. User can follow user
3. User can mark post as favorite
4. Service displays user's timeline
5. Tweets can contain photo/video

### NFR
1. Highly available
2. Timeline generation latency < 200ms
3. Consistency can take a hit

### Extended requirements
1. Searching
2. Replying
3. Tagging users
4. Tweet notification

## Capacity estimation and constraints

1. Total daily active users - 200M
2. Tweets daily - 100M
3. User follows 200 other users on average
4. 5 favorites a day per user `200M * 5 = 1B`
5. Tweet views - user views timeline 2 times a day and visits 5 other users
`200M * (2 + 5) * 20 = 28B/day views`
6. Storage - each tweet 140 characters (2 bytes per character), 30 bytes per metadata
`100M * (280 + 30) = 30GB/day`
7. 5 years storage, assume every 5th tweet contains photo (200kb), every 10th has 
video (2MB) - `(100M/5 * 200KB) + (100M/10 * 2MB) ~ 24TB/day`
8. Bandwidth - 24TB/day = 290MB/sec. We have 28B/day views
```
(28B * 280 bytes) / 86400 = 93MB/s
(28B / 5 * 200KB) / 86400 = 13GB/s
(28B / 10 * 2MB) / 86400 = 22GB/s
-------------
Total ~ 35GB/s
```
## System API
REST API
tweet(api_key, tweet_data, tweet_location, user_location, media_ids): string|error

## High level design

1. 100M/86400 = 1150 tweet writes/second
2. 28B/86400 = 325K tweet reads/second

Read heavy system

```
                            App server|<----------------->Databse
client----->LB------------->App server|
                            App server|<----------------->File storage
```

## DB schema
1. Tweet
2. User
3. UserFollow
4. Favorite

## Data Sharding

### UserID based

Easy to maintain but:
1. User can become hot - uneven traffic
2. User can have many tweets - not balanced DBs

### TweetID based

1. App queries followed users
2. App server sends query to different DB servers
3. App server aggregates results

It solves hot user problem, but we now have to query multiple DBs == latency
We can place cache in front of DB servers to speed up hot tweets serving.

### Tweet creation time
Easy to fetch latest news but load will become uneven

### TweetID + Creation time

Split id into two parts:
1. Creation timestamp
2. Unique id

Reset unique id each second

We'll still have to query multiple DB servers, but since time and id are in one column
it'll be quicker since only one index is involved.

## Cache

Place cache before DB servers.

Eviction policy:
1. LRU - good choice
2. 80/20 - cache the most popular 20% of the tweets

## Replication and fault tolerance

Since it's read-heavy we'll have multiple DB shards. Writes will go to the primary 
server which will propagate data to the rest.

## LB
1. Clients / Application
2. Application / DB replicas
3. Aggregation server / Cache server

We can go with round robin approach, if needed swith to more intelegent scheme and check
each box load.

## Monitoring

1. New tweets per day/second - will give peak times
2. Timeline delivery stats - how many tweets per day/second system delivers
3. Average latency for timeline display

## Extended requirements

### Server feeds
Server number of feeds depending on viewport size, use pagination.

User pregenerated feeds

### Retweet

Store only id of the tweet, content is the same

### Trending topics

Cache most frequently occuring hashtags