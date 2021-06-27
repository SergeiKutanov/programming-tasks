# Twitter search

## Requirements

### FR
Assume:
1. Twitter has 1,5B total users
2. 800 DAU
3. Tweet size ~300 bytes
4. 500M searches/day
5. Search query will consist of multiple words with AND/OR

We need to design a system to efficiently store and query tweets

## Capacity estimation and constraints

### Storage
Since we have 400M new tweets/day and each tweet is 300 bytes. So total
`400M * 300 = 120GB/sec`

Per second
`120GB / 24 hours/ 3600 sec ~ 1.38 MB/sec`

## System API
```
search(dev_key, search_terms, max_results, sort, page_token): JSON
```

## High level design
We need to store all the statuses in a databse and build an index that can keep
track of which word appears in which tweet. The index will hel us quickly find tweets
that users are trying to search.
```
clients<--------->application server------------>storage server
                        |
                        |
                        V
                    Index server
```

## Detailed component design

### Storage
We to store 120GB if new data every day. Given huge amount of data we need to come up
with a data partitioning scheme that will be eficiently distributing the data onto
multiple servers. Next five years:
```
120GB * 365 days * years = 200TB
```

If we never wnat to be more than 80% full, we need 250TB. If we want to keep an extra
copy - 500TB. Assuming a server hold 4TB we need 125 such servers.

Let's start with a simple design with one MySQL db. We'll store tweets in a table
with two columns TweetID and TweetText. Assume we partition data based on TweetID. 

#### How to create system-wide unique TweetID

If we're getting 400M new tweets a day, then how many objects we can expect in 5 years
```
400M * 365 days * 5 years = 730B
```
This means we'd need a 5 bytes number to identify TweetIDs uniquely. We can have a 
separate service to generate IDs.

### Index
Let's build index that can tell us which word comes in which tweet object. Let's 
estimate index size. All english words + some famous nouns like people names,
city names, etc. If we have ~300K words and 200K noues, then 500k total words.
Assume average length of word 5 characters. If we're keeping the index in memory,
we need 2.5MB of memory to store all the words:
```
500K * 5 = 2.5MB
```
If we want to keep the index in memory for all tweets from last two years. Since we 
will be getting 730B tweets in 5 years, this will give us 292B tweets in two years.
Given each TweetID will be 5 bytes:
```
292B * 5 = 1460GB
```
So out index would be like a big distributed hash table. Key - word, value - a list
of TweetIDs of tweets containing the word. Assuming there are 40 words in each tweet
and since we'll not be indoxing prepositions and other small words, we'll assume we 
have around 15 words in each tweet. This means each TweetID will be stored 15 times 
in our index. So total memory we'll need:
```
(1460 * 15) + 2.5MB = 21TB
```
Assuming a high-end server has 144GB of memory, we would need 152 such servers to 
hold our index.

## Sharding
### Based on words
While building the index we iterate over all the words and calculate the hash
of each word to find the server where it would be indexed. To find all tweets 
containing a specific word we have to query only the server containing the word.

Issues:
1. What if word becomes hot? Lots of queries on the server holding the word. We'll
get high load.
2. Over time some words can end up storing a lot of TweetIDs compared to others.
It'll be hard to maintain uniform distribution of words.

To recover form this we'll have to repartition or use consistent hashing.

### Based on tweet object
While sotring, we'll pass the TweetID to our hash function to find the server
and index all the words of the tweet on that server. While querying for a 
particular word, we have to query all the server, and each server will return a set 
of TweetIDs. A centralized server aggregates these results.

```
client-------> load balancer------>app servers
                                        |
                                        |
                                        V
                                 Aggregator servers<---->Databases
                                        |                   |
                                        V                   V
                                Index servers<----------->Index-builder server
```

## Fault tolerance
We can have master-slave configuration. Slave will take over if master dies.
If both die we'll have to spin up a new server. But how will we rebuild the trie?
The brute-force solution is to iterate through the whole database and filter 
TweetIDs using out hash function to figure out al the required tweets that would
be stored on this server. This is very inefficient.

To improve we can build a reverse index that will map all the tweetIDs to their
index server. Our index b uilder can hold this information. We'll need to build a 
hashtable where key - index server number, value - hashset containing all the 
tweetIDs being kept at that index server. Using hashset gives us O(1) access to 
quickly add, remove tweets.

## Cache
We'll place a cache server in from of our database. LRU will work fine.

## Load balancing
1. Between clients and application servers
2. Between application server and backend server

We can use round robin at first and switch to more intelegent schems if needed.

## Ranking
Assume we want to rank tweets by popularity, like how many likes or comments a
tweet is getting. In such case our ranking algo can calculate a popularity number
and store it with index. Each partition can sort the resilts based on this 
number before returning results to the aggregator server. The aggregator combines
all these results, sort them based on the popularity number, and sends the top
results to the user.