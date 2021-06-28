# Yelp

## Requirements

### FR
1. Users can crud places
2. Given location find nearby places within a radius
3. Users can add feedback

### NFR
1. Real-time search
2. Supports heavy search load

## Scale estimation
Assume 500M places, 100K QPS, 20% annual growth of QPS and places.

## Database schema

1. Location ~ 793 bytes
2. Places

## System API

```
search(key, terms, user_location, radius, max_results, sort, page): json
```

## Basic design and algo
We need indexes for things to search. The indexes should be read efficient.
Since location of places doesn't change very often, we don't need to worry about
frequent updates.

### SQL solution
To find all nearby places of a given location within a radius, we can query.
But the query won't be efficient. We have estimated 500M places to be stored.
Since we have two separate indexes, each index can return a huge list of places and
performing an intersection on those two lists won't be efficient.

### Grids
We can divide the whole map into smaller grids. Each grid will store all the places residing within a specific
range of longitude and latitude. Grid size could be equal to the distance we would like to query. If it's equal
we'd need to query for the location grid and 8 adjecent ones. We can keep the index in memory, hash table where 
key is the grid number and valie is the list of places.

#### Memory
Radius 10 miles, total Earth area 200M square miles. We'd need 4 bytes to identify each grid, locationID 8 bytes
```
(4 * 200M) + (8 * 500M) = ~4GB
```
The solution can still run slow for grids with many places

### Dynamic grid sizes
We should have smaller grid sizes for more populated areas. We can set a threshold for a number of places in a grid.
On the threshold is reached we split the cell in 4.

For this we can have a tree with 4 children nodes (QuadTree).

To find a location we'll start at root and search downward. At each step, we'll see of the current node has children.
If yes, we'll move toward the child containing the location. If not - that's our node.

How to find neighbouring grids? Since only leaf nodes contain the places we can connect them with doubly linked list.
Or we can keep links to parent nodes and go back to parent to find neighbours. Once we have locationID, we can query
DB.

**Flow:**
We'll find user's locationID. If it has enough places we'll return them. If not we'll keep expanding to the neighbours.

#### Memory
If we only cache locationid and lat/long `24 * 500M = 12GB`

Since each grid can have a max of 500 places and we have 500M location
```
500M / 500 = 1M grids
```
We'll have 1M leaf nodes + ~1/3 internal nodes + 4 pointers to children 8 bytes each
```
1M * 1/3 * 4 * 8 = 10MB
```
So total memory - 12.01 GB

To insert a place we need to add it to DB and QuadTree. If the tree is on one server - easy, if no we'll need to first
find right location using hash function.

## Data partitioning
### Region dased
We can split based on ZIP code. Problems:
1. Hot places
2. Growing number of places in a region

### LocationID based
We'll iterate through all the places and calcualte the hash of each LocationID to find a server where it would be 
stored. To find a place we'll have to query all server, centralized server will aggregate.

```
client----->Load balabcer------>Aggregation servers------>quad tree servers--->quead tree index--->databases
```

## Replication and fault tolerance
We can have replicas for quad tree servers. We'll put them into master-slave.
Read traffic will go to slaves, writes first to master then propagated to slaves.

If both master and replica die we can use a reverse index which will map places to quad tree server.
We'll need to build a hash map where key is the quadtree server number and the value - is a hashset containing
all the places being kept on this server.

## Cache
Put the cache in front of DB with LRU

## Load balancing
1. Clients / App server
2. App server / Backend server
Round robin or load check

## Ranking
We can have a popularity number and store it in quad tree and db.