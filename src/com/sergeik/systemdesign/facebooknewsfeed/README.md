# Facebook newsfeed

## Requirements

### FR
1. Generated based on the posts from people, pages, groups
2. User can have many friends and follow many groups
3. Feeds may contain images, videos or just text
4. Service appends new posts when they are published

### NFR
1. News feed generated in real time, max latency - 2s
2. Post propagates to other user in no longer than 5 seconds

## Capacity estimation and constraints

1. User has 300 friends and follows 200 pages
2. Traffic: 300M DAU with each fetching the feed 5 times a day. 1.5B feed requests or
~17.5K requests/second
3. Storage: 500 posts stored in memory, each post 1KB. We have ~500KB per user. For all
users - 150TB. If a server can hold 100GB of memory we need 1500 servers to keep 500
posts in memory for all active users

## System API
```
getUserFeed(dev_key, user_id, since_id, count, max_id) : JSON | error
```

## Database design
Objects: user, entity (page, group), feeditme.

Observations:
1. User can follow other entities and become friends with other users
2. Both users and entities can post with video/image/text
3. Each feeditem will have userId (omit groups, pages) who post it
4. feeditem has optional entity id indicating if post was made in entity

If we go with relational DB we have two relations:
1. User-Entity
2. FeedItem-Media

## High level system design


### Feed generation

1. Retrieve IDs of all users and entities Jane follows
2. Get latest, most popular posts ids
3. Rank the posts
4. Store the feed in cache
5. On the front end paginate results

If Jane is online new posts should be injected in the feed. We can periodically perform
the above steps to rank and add the newer posts. Jane can be notified about them.

### Feed publishing

Components:
1. Web servers - maintain connection with user, transfer data to user
2. App server - executes workflow of storing new posts in the DB. Pushes and retieves
newsfeed
3. Metadata database and cache - store metadata
4. Posts database and cache
5. Video and photo storage and cache
6. Newsfeed generation service
7. Feed notification service

```
client A---adds post-->web server----->app server---------->metadata cache<--->MD DB
                                            |
                                            |
                                    Newsfeed gen service--->Posts cache<---->Posts db
                                            |       |
                                            |       |------>Media cache<->Media storage
                                            |
                                            |
            feed notification serv--->cache servers holding feeds
                        |                       |
                        |                       |
User B--|               |                       |
        |feed update    |                       |
        |----------Web server----get feed--->app server
        |    
User C--|

```
 
## Detailed component design

### Feed generation

Direct query ot DB will have issues:
1. Slow fo rusers with a lot of friends
2. We generate the feed when user loads page == latency
3. For live updates each status update will result in feed updates for all followers
== backlog in newsfeed generation service
4. Live updates produce heavy load for people with many followers.

### Offline generation for newsfeed

We can have dedicated servers to continuously generate the feed. When we need to 
generate a feed, we check the time of last feed. We can store the data in a hash table
where key == userid and value is
```
STRUCT {
    LinkedHashMap<FeedItemID, FeedItem> feedItems;
    DateTime lastGenerated;
}
``` 
We can store FeedItemIDs in a LinkedHashMap or TreeMap, which will allow us to jump
to any feed and easily iterate the map. User sends feedItemId and we server the next
pregenerated feeditem

#### How many feed items to store
We can start with 500, but this number can be adjusted depending on how many posts
a user sees in his viewport.

#### Should we generate and keep in memory newsfeed?

The will be many users who don't login frequently. Things to do to handle:
1. use LRU cache to remove feed items that haven't been access for a long time
2. figure out login pattern of users to pre-generate the feed according to it.

### Feed publishing

#### Pull model
All recent feed data is kept in memory and user pulls it form the server on demand.
1. New data is not shown until user pulls
2. Hard ro find right pull cadence - empty responses

#### Push model
We can go with Long Poll request to get the updates right away, but this will produce
heavy loads for users with many followers

#### Hybrid
Use pull for celebrities and push for users with few followers

## Feed ranking
The easiest way is to rank based on creation time, but modern systems use way more
sophisticated algorithms. They use a set of features that are relevant to the 
importance of any feed item.

## Data partitioning

### Sharding posts and metadata

User combination of timestamp and id (see Tweeter)

### Sharding feed data

Sahred based on user id. We can try storing data of a user on one server, it should not
exceed memory limits, we dont' expect to store more than 500 FeedItemIDs.