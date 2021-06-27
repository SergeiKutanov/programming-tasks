# Typeahead

## Requirements

### FR
1. as a user types in a query, server suggestts top 10 terms starting with prefix
endtered

### NFR
1. Suggestions appear in real time.
2. User should be able to see the suggestions within 200ms

## Basic design and algorithm

Trie will be the most appropriate data structure. To save space we can merge nodes
with one character. We'll go with case insensitive trie for simplicity.

### How to find suggestions
We can store the count of searches that terminated at each node.

### Time to traverse the trie
Although this is the optimal data structure considering how much data we have traversal
still can take significant time. Since latency is extremely important to us we should
look for an improvement.

### Can we store top suggestions at each node
We can but this will drastically increase the memory needed. To optimize this we can 
store just references to those nodes.

### How to build the trie
We can go with bottom-up approach. Each parent will get suggestions from its children,
merge them and keep the top 10.

### How to update the trie
Assuming 5B suggestions per day, we'd have ~60k queries/second. We can't update the trie
on each new query.

We can log the queries and frequencies as they come in. We can either log all of them
or do sampling and log every 1000th. If we don't want to show a query that was asked
for less than 1000 times, we don't log it.

We can have map-reduce setup to process the logs, say every hour. These MR jobs will
calculate frequencies of all searched terms in the past hour. We can then update the 
trie with the new data. But we should do this offline.
1. We can make a copy of the trie. Once update is done replace the old with the new one.
2. Or we can have master-slave configuration. We'll update slave's trie and then make 
it a master.

### How can we update frequencies of suggestions

We can update only differences. If we're keeping count of all the terms
search in the last 10 days (sliding widnow), we'll need to subtract the counts from 
the time period no longer included and add counts for the new time period being
included.

After we insert a new term into the trie, we'll go to the terminal node of the phrase
and increase its frequency. Since we're storing 10 top suggestions it's possible
that this term will jump into few parent nodes. So, we need to update the top
10 queries of those nodes too. We have to traverse from the node to the root and 
update suggestions if needed.

### How to remove term

We'll just remove it when regular update happens. If we need to stop serving the
suggestion immediatelly, we can have a filter that will filter out the specific
results.

## Permanent storage of the trie

We need to find a way to recover the trie in case machine fails. We'll just store
the structure in a file. We can start with the root node and save the trie 
level-by-level. With each node, we can store what character it contains and how
many children it has. Right after each node, we should put all of its children.
`C2,A2,R1,T,T,O1,D`.
We don't store the counts and suggestions. We'll have to recalculate them while 
building the trie.

## Scale estimation
1. We expect 5B searches/day == 60K queries/second

Since there will be lots of duplicates, we can assume that only 20% will be unique.
If we only want to index the top 50% of the search terms, we can get rid of a lot of
less frequently searched queries. Let's assume we have 100M unique terms for which
we want to build an index.

**Storage estimation:** On average each word consists of 5 characters, query consists
of 3 words. Assuming we need 2 bytes to store a character we'll need 30 bytes per 
query. `100M * 30 bytes = 3GB`.

We can expect some growth of the data, but we should be also removing some terms 
that are not searched anymore. If we assume we have 2% new queries every day and if 
we are maintaining our index for the last one year, total storage:
```
3GB + (0.02 * 3GB * 365 days) = 25GB
```

## Data partition

### Range partitioning
We map parttions by the first character of the query. But it will lead to unbalanced
servers.

### Based on max server capacity
We can try to build the trie on one server until its memory allows it. Once we reached
the limit, we break out partition there.
1. Server A - A-AABC
2. Server B - AABD-BXA
3. Server C - BCB - CDA...

We can have a LB before trie servers which will map request based on the query.
We need to merge the results at the server. We can introduce aggregator which will
do the job.

This approach can still lead to hot spots. We can have many terms starting with 
`cap`.

### Based on the hash of the term
Each term will be passed to a hash function, which will generat a server number 
and we will store the term on that server. This will make our term distribution
random. To find suggestions we'll have to ask all the server and then aggregate
the resilt.

## Cache
We should cache all top searched terms. There will be a small percentage of queries
that will be resposnsible for most of the traffic. We can have separate cache
server in front of the trie servers.

We can also build a Machine Learning model that can try to predict the engagement
on each suggestion based on personalization.

## Replication and LB
We should have replicas of trie servers, both for load balancing and fault tolerance.
We also need a load balancer that keeps track of our data partitioning.

## Fault tolerance
We can have master-slave configuration. If master dies, slave takes over.

## Client

1. Should hits server if user hasn't typed for > 50ms
2. Cancel in-progress requests if user is typing
3. Prefetch some data from server
4. Establish early conneciton with thecserver
5. Server can push some part of their cache to CDNs