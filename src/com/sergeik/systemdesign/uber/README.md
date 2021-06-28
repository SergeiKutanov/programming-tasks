# Uber

## Requirements

### FR
Two types of users: drivers and customers.

1. Drivers can notify about their current location and their availability
2. Passenger see nearby drivers
3. Customer can request a ride, drivers are notified
4. Once ride is accepted by both parties, they can see each other's location
5. Upon reaching the destination journey marked as completed and driver becomes available

## Capacity estimation and constraints

1. 300M customers, 1M drivers. 1M daily active customers and 500K acti ve daily drivers
2. 1M daily rides
3. Location update rate 3s
4. Once a customer puts in a request for a ride, the system should be able to contact drivers in real time

## Basic design and algo
We'll take design from Yelp but we need to constantly update drivers/customer location. This becomes a bottleneck
with the QuadTree.

### Do we need to update QuadTree on each location update?
We can keep location updates in a hash table and update the tree less frequently. Let's assume we can garauntee the 
location become visible within 15 seconds. Meanwhike we'll maintain a hash table that will store the current location
reported by drivers - DriverLocationHT.

### Memory
We need to store DriverID, their present and old location ~ 35 bytes. If we have 1M drivers - 35MB.

### Bandwidth
We're sending driver id and location - 19 bytes. We send it once in 3 seconds, hence 19MB in 3 seconds.

### Distribution
Although it will easily fit into one server we shoyld still dsitribute for falt taulerance and load distribution.
We also need to:
1. Update interested customers once driver's location updates
2. The server needs to notify respective QuadTree servers to update driver's location.

### How to broadcast location updates to cusomers?
We can have Push Model. We can have a dedicated notification service based on publisher subscriber model.

### Memory to keep subscriptions
We have 1M daily active customers and 500K drivers. Assume 5 customers subscribe to a driver.
```
(500K * 3) + (500K * 5 * 8) ~ 21MB
```

### Bandwidth
5 subscribers - `5 * 500K = 2.5MB`

To all customers we need to send DriverID (3 bytes) and location (16 byes) per second
```
2.5M * 19 bytes = 47.5 MB/s
```

### How will new publishers/subscribers get added for a current customer?
Clients can pull it form the server by sending their current location

### Do we need to repartition the grid when it grows to a threshold?
We can have a cushion.

```
customers<-------->load balancer----------->aggregation servers
    |-----------|                                   |
                |                                   |
        notification server                 QuadTree Server<----QuadTree index---->databases
                |       |                       ^
    |-----------|       |-----------|           |
    |                               |           |
drivers---->load balancer------->driver location server----->SSD storage
```

#### Request ride flow
1. Customer puts request in
2. One of the aggregator servers will taks it and ask QuadTree server to return nearby drivers
3. Aggregator collects the results and sorts them by rating
4. The Aggregator service sends notifications to 3  drivers simultaniously. If none accepts - sends to next 3.
5. Once a driver accepts, customer is notified

## Fault tolerance and replication

We'd need replicas of Driver location server and notification. We can also use SSD for improved IO.

## Ranking
We'll have a popularity number and keep it our DB.