# Netflix/YouTube system design

## Requirements

### FR

1. Users upload videos
2. Users share and view videos
3. Users search based on video titles
4. Service records stats
5. Users add/view comments on videos

### NFR

1. Highly reliable, videos are never lost
2. Highly available, concistency can take hit
3. Real time experience while watching videos

### Not in scope

Video recommendations, most popular videos, channels

## Capacity estimation and constraints

1. 1.5B total users
2. 800M active daily users
3. User views 5 videos / day `800M * 5 / 86400 = 46K videos/s`
4. Upload:view ration 1:200 - `46K/200 = 230 videos/s` are uploaded
5. Storage: assume 500 hours of video uploaded every minute, 1 video minute = 50MB
`500 hours * 60 min * 50MB = 1500GB/min = 25GB/sec`
6. Bandwidth: with 500 hours of video assuming bandwidth 10MB/min per upload
`500 hours * 60 min * 10MB = 300GB/min = 5GB/sec`

## System API

1. uploadVideo(dev_key, video_title, video_descr, tags[],
                cat_id, default_lang):string|error
2. searchVideo(dev_key, query, user_location, max_videos_to_return,
                page_id) : json | error
3. streamVideo(dev_key, video_id, offset, codec, resolution): stream | error

## High level design

1. Processing queue - each video pushed to the queue and later picked up by other
component
2. Encoder - encodes video into different formats
3. Thumbnail generator - generates thumbnails for videos
4. Video and Thumbnail storage - distributed file storage
5. User database
6. Video metadata storage

```

client---->web server<------->app server---->processing queue----->encoder
                                                                        |
                                                                        V
                                user db         metadata db         file storage
```

## Database schema
1. Video
2. Video comment
3. User data storage - MySQL

## Detailed component design

### Video file storage
HDFS or GlusterFS - distributed file storage

### How to manage read traffic

We should segregate the read traffic from write. Since we'll have multiple copies of
videos we can distribute the traffic.

For metadata we can go with master-slave configuration. This might produce stale data
but this can be acceptable.

### Where to store thumbnails

We will have at least 5 thumbnails for each video. So this is a huge traffic. We must 
remember:
1. Files are small ~5KB
2. Read traffic huge - users waht one video but see 20 thumbnails

Storing them on disc is not acceptable, extremly high latency.

Bigtable can be a reasonable choice as it combines multiple files into one block to
store on the disk and is very efficient in reading a small amount of data.

### Video uploads

Videos can be big, broken upload should resume from the point of failure.

### Video encoding

Uploaded files stored on a server and then are picked by encoder from the processing
queue. Once completed video will become available.

```
                                User management         Metadata DB
                                        |------------------|--------------------|
                                        |                                       |
client<------->web server<--------->app server------>processing queue------>Encode
    |               |                   |                                   |
   CDN<-----Video/Image cache<-------Video storage              BigTable thumbnail
``` 

## Metadata sharding

### By userId
1. What if user becomes popular?
2. Over time users can have too much video data

### By videoID
Hash function finds server where we store metadata.

To find user's videos we query all servers, each server responses, a centralized server
aggregates the results. We'll still have proble with popular videos, but we can place 
cache to store hot videos in front of DB servers.

## Video deduplication
Problems:
1. Storage waste
2. Caching same videos
3. Network usage overload
4. Energy consumption

We can do inline deduplication to avoid unnecessary server load and network transfers.
We can replace lower quality video with a higher one, we can upload only not duplicated
parts of a video.

## LB

We should use consistent hashing. We might still get uneven load in case of popular 
videos. We can dynamically redirect to less busy server with the same video, but this
will bring latency before starting video due to multiple redirects possible.

## Cache
We can have cache for metadata db. LRU cache will work, if not enough go with 80/20

## CDN
The most popular videos should be served form CDNs.
Less popular can be served directly from our servers.

## Fault tolerance
We use consistent hashing for distribution among database server. This will help
in rpelacing a dead server and aslo distribute load.

