# Door dash food delivery

## Actors
1. Customers/consumers
2. Restaurant/Merchant
3. Doordashers/Drivers
4. DoorDash admin (from the company)

## Asumptions
1. Unlimited food is available
2. Restaurants don't have their own online ordering system
3. Customers will be shown restaurants within a particular radius (10 miles)
4. Customers are allowed to order food from one restaurant at a time. Menu items can be combined.

## Functional requirements
### Customers
1. Search for restaurants by cuisine type, menu items
2. Create a cart, add menu items
3. Receive notifications about order status
4. Track status of the order
5. Cancel the order
6 Pay for the order
7. Create/Update their account and contact info

### Restaurants
1. Create profile
2. Receive notification about order placed, assigned driver and update the status of an order
3. Offboarding

### Drivers
1. Receive notifications about available orders in the area
2. Know when an order is available for pickup
3. Inform restaurant and customer about problems
4. De-register in case they don't want to continue with the job

## Not-functional requirements
1. Latency
2. Consistency - menu items should be the same when the order is made and is processed.
3. Availability
4. High throughput - the system should be able to handle high peak load without problems.

## Out of scope
1. Restaurant rate
2. Reporting
3. Driver onboarding and payout
4. Restaurants payout
5. Mapping capability to show location of driver
6. ETA calculation

## Capacity estimation and constraints
Assume we're service users in 10M area codes. On average each area code has 100 restaurants. 
Each restaurant can have 15 dishes. Total records:
```
10M * 100 * 15 = 15B
```
Number of customers - 20B. If each customer on average places 2 orders a day, number of orders = 40M.

In general, the searching of menus/restaurants will be read-heavy and the ordering will be
write-heavy.

## Data model
We'll omit details and just list main entities

|Entity|Description|
|------|-----------|
|Address|Customer address|
|Person|Customer|
|Discount code|  |
|Payment|  |
|Order|
|Business|
|Menu|
|Order item|
|Menu Item|
|Menu item option|

## Data storage
We'll go with combination of storages.

Since we'll have large amount of data we can go with a NoSQL like Cassandra which will allow easy
scaleability and also give freedom for data structure as restaurants might be very different.

Pictures can be stored in an object sotrage service like S3.

Ordering is transactional process and can be stored in SQL DB.

## Component design and architecture
Overall architecture can be microservices-based with heavy usage of publisher-subscriber pattern.
This makes services decoupled.
Also it's important to use database-per-service.
We showed data model as one big database, but to adhere to database-per-service we'll need to do 
functional partitioning.

### Services
1. Restaurant search service
2. Ordering service
3. Restaurant profile service
4. Data indexer
5. Order fulfillment service
6. External Payment Gateway
7. User Profile Service
8. Preference Service
9. Driver dispatch service
10. Restaurant profile service
11. External payment gateway
12. Notification server

### UI client
The app will be access via mobile, web. There will be four versions per actor.

### Search ecosystem
This is an entry point for most customers and will be read-heavy. We can make user of products like
Elasticsearch for quick lookup. It's open source and distributed.

We'll need to have a queue in place to process async updates to the search cluster. When the 
Restaurant profile service creates/updates a restaurant/menu it can post an event to the queue. Data 
Indexer will pick up the event and runs a query against the database to formulate a document as per 
the correct format, and posts the data into the search cluster. We also need to have a Restaurant 
Search Serivce which executes queries on the search cluster based on user input and returns the result
to UI.

Elasticsearch has a geo-distance query which can be leveraged to return restaurants within some area.

### Ordering service
Will manage the menu selection, shopping cart and placement of orders. It will process payment using
an External Payment Gateway and persist the results into Orders DB. 

Customers will be able to get receipt, cancel the order, view past orders.

### Order fulfillment service
High-level functionalities
1. Restaurant accepts order
2. Notifies customer of order status
3. Allows customers to check the status of order
4. Drivers can check if order is ready for pickup
5. Notifies driver when order is ready for pickup

### User profile management & preference services
The services will allow persisting of profile and preferences on the system actors.

### Driver dispatcher service
1. View pickup orders from a list that they can accept
2. Accept an order
3. View past orders
4. View customer information

### Restaurant profile service
1. Onboard
2. Update/Delete profile
3. CRUD menus
4. Upload images
5. View financial details
6. Setup payment method

### External payment gateway
Will interface payment gateway like PayPay, Stripe. The Order Service will interact with it to
ensure payment is done. The interaction should be synchronous.

### Notification service
Will be sending notifications to system actors.

## Data partitioning
Restaurants can be partitioned based on:
1. Area code
2. Restaurant ID
3. Menu items
4. Combination of area code and restaurant id

## Replication and fault tolerance
We need to identify single point of failures and try to have replicas for those services. Each 
service should be scalable horizontally. The NoSQL infrastructure will also have multiple nodes.
Queues can have partitions and replication as well. Autoscaling can be enabled to spin up new 
instances if needed.

## Caching
Based on the recent orders in the area of the most ordered items we can maintain cache with least
recently used policy or least frequently used. Images can also be cached to avoid hitting object 
storage each time.

A content delivery network (CDN) could also be used as a cache in order to make local content 
available to users based on their geo location.

## Security
1. HTTPS/SSL-TLS for securing the wire.
2. OAuth2.0 for token authorization

## Load balancing
Load balancers can be placed in front of each service. It will make sure no instances in overloaded
and will latency is avoided. Round robin policy can be used, or more sophisticated like pinging 
services for their load.
