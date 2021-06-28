# Web crawler

## Requirements

Scalability, extensibility.

## Considerations
1. Is it a crawler for HTML pages only? Or should we fetch and store other types of
media?
2. What protocols are we looking at?
3. What is the expected number of pages we will crawl? Let's assume 15B records
upper limit.
4. Should we consider robots.txt and how to implement it?

## Capacity estimation and constraints

We want to crawl 15B pages in 4 weeks
```
15B / (4 * 7 * 86400) = 6200 pages/sec
```
Assume 100kb average page size + 500 bytes of metadata
```
15B * (100KB + 500) = 1.5PB
```
Assuming 70% capacity model
```
1.5PB / 0.7 = 2.14PB
```

## High level design

### Basic algo
1. Pick a URL from invisited list
2. Determin IP address
3. Connect and download contents
4. Parse contents to find new URLs
5. Add the URLs to queue
6. Process the downloaded doc
7. Go to step 1

### How to crawl
BFS or DFS? BFS is usually used

Path-ascending crawling. Check path sections up to the root. If we have 
`http://a/b/c` check also `http://a/b` and `http:/a`

### Dificulties
1. Large volume of pages. It should be intelegent enough to prioritize the pages
it downloads, since we can't crawl all pages
2. Rate of change on web pages. We should keep in mind that websites can be dynamic.

Components:
1. URL frontier - to store the list of URLs to download and prioritze
2. HTTP fetcher = downloads page
3. Extractor - extracts links from document
4. Duplicate eliminator
5. Datastore

```
            duplicate remover
                    |
                    V
internet<----->HTML fetcher------->Extractor------->data store
                    |                 |
                    -------------------
                        |
                    URL frontier
```

## Detailed component design

### URL frontier
Is the data structure that contains all URLs that remain to be 
downloaded. Do BFS traversal. We can distribute the service by mapping URL
to a specific server. We should be polite:
    1. Should not overload a server by downloading a lot of pages from it
    2. Only one machine should connect to it
To implement it we can have a collection of distinct FIFO queues on each server.
Each worker thread will have its separate sub-queue, from which it removes URLs for
crawling. When a new URL needs to be added, the FIFO sub-queue in which it's placed
will be determined by the URL's canonical name. Our hash function can map each
hostname to a thread number.

It'll be large and we have to use files. The queue can have separate buffers for 
enqueuing and dequeuing . 

### Fetcher
Downloads page contents. To avoid downloading robots.txt at each request we can
store in cache.

### Document input stream
Our design enables document processing by several modules. To avoid downloading
document multiple times, we cache the doc locally using DIS.

DIS is an input stream that caches the entire doc. It can keep it in memory of small
or use a backing file.

Each worker thread has an associated DIS, which it requests from doc to doc. After
extracting a URL from the frontier, the worker passed that URL to the relevant protocol
module, which inits the DIS from a network connection to contain doc content. The worker
then passed the DIS to all relevant prcessing modules.

### Document dedupe test
Calculate 64-bit checksum (MD5 or SHA) and keep it in DB. Use it to determin if a 
duplicate.

We'll use unique set:
```
15B * 8 bytes = 120GB
```
We can cache on each server with everything backed by persistent storage.

### URL filters 
We'll have a blacklist of websites to ignore. URL frontier will check it before 
adding a url

### Domain name resolution
We can cache DNS results

### URL dedupe
Store checksum of canonical URL. We'll keep a cached copy on each server.

Memory:
```
`15B * 4 bytes = 60GB
```

### Checkpointing

We need to do snapshots of its states to the disk.

## Fault tolerance
We should use consistent hashing for distribution amonge crawling servers. Will help
in replacing a dead node and distributing load. All our server will be performing
checkpointing and storing their FIFO queues to disks. So we can easily restore the 
state.

## Data partitioning
We have types of data
1. URL to visit
2. URL checksums
3. Doc checksums

Since we are distributing URLs based on the hostnames, we can store these
data on the same host.

## Crawler traps
It's a URL or set of URLs that cause a crawler to crawl indefinitely.