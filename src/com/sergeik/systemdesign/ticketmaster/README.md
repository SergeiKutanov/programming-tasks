# Ticketmaster system design

## Requirements
### Functional requirements
1. List different cities where its affiliate cinemas are located
2. Once a city is selected it should display movies in it
3. Once a movie is selected it should display cinemas
4. User can choose a show at a particular cinema and book their tickets
5. Displays seats and allows seat selection
6. Distinguish available seats from booked
7. User can put a hold on a seat for 5 minutes before they make a payment
8. User can wait if there's a chance a seat becomes availble due to other user hold expiring
9. Customers should be served in first come first served manner.

### Non-functional requirements
1. Highly concurrent.
2. Since system deals with financial transactions we should be ACID compliant.

### Design considerations
1. Assume no authentication
2. No partial orders, user buys all tickets
3. Fairness is mandatory
4. To stop system abuse we can restrict users from booking more than 10 seats at a time
5. Should be scalable to handle high peak events.

### Capacity estimation
#### Traffic
3B page views per month. 10M tickets per month sales
#### Storage
500 cities, on average 10 venues per city, 2000 seats per venue, 2 shows daily.

Let's assume each seat booking needs 50 bytes to store in the database. We'd also need to store info about events and
venues. Let's assume it also takes 50 bytes per record. All data total:
```
500 cities * 10 cinemas * 2000 seats * 2 shows * (50 + 50) bytes = 2GB/day
```
5 years = 3.6TB

## System APIs
We can have SOAP or REST.
```
searchMovies(api_key, keyword, city, lat_long, radius, start_datetime, 
            end_datetime,postal_code, results_per_page, sorting_order): JSON
reserverSeats(api_key, session_id, event_id, show_id, seats_to_reserver[]): JSON with status
//show_id - time of event
```


## Database design
1. Each city can have multiple venues
2. Venue has multiple halls
3. Each event has multiple bookings
4. User has multiple bookings

## High-level design
```
                                                                    -------------->Cache servers 
                                                                    |
                                                                    |       
clients<------>Load balancers<------>Web servers<------->Application servers<------>Databases
```

## Detailed component design
At first assume the service is served from one server.

**Ticket booking workflow**
1. User searches for a event
2. Selects event
3. Selects available shows
4. Selects a show
5. Selects number of seats
6. If the number of seats is available shown a map of seats. Otherwise directed to #8
7. Once user selects seats, system tries to reserve
8. If seats can't be reserved:
    1. Show is full, display error
    2. There are other seats available - take user to venue map
    3. No seats available but there are other users with temp hold, then user can wait:
        1. If seats become available - go to map
        2. While waiting, if all seats get booked - show error
        3. User cancels the wait
        4. At maximum user can wait for one hour, then session gets expired and the user is taken to event
        selection page.

9. If seats are reserved the user has 5 minutes to pay, otherwise the seats are freed.

**How would the server keep track of all the active reservations that haven't been booked yet? Same for awaiting 
customers? We'll need two services:
1. ActiveReservationService - keeps track of all active reservations and remove any expired ones
2. WaitingUserService - keeps track of waiting user requests, as soon as the required number of seats become 
available, notify the longest waiting user.

### ActiveReservationService
We can keep all the reservations of a show in memory in a data structure similar to LinkedHashMap or a TreeMap in
addition to keeping all the data in DB. We'll need a linked hash map kind of data structure which will allow us to 
jump to any reservation to remove it. Since we have expirtion time the head of the HashMap will point to oldest and
will be removed.

To store every reservation for every show, we can have a HashTable where the key would be show Id and the value will 
be LinkedHashMap containing booking id and creation timestamp.

In the DB we'll store the reservation in the Booking table and the expiry time will be in the Timestamp column.
Status field will have a value of:
1. Reservered
2. Booked
3. Expired

We'll remove the record from LinkedHashMap of the relevant show. When reservation is expired we can either remove it
from the table or change status to expired.

The service will also work with financial service to process user payments. Once a booking is complete or reservation
is expired WaitingUserService will get a signal.

### Waiting user service
Structure will be almost identical to ActiveReservationService, but the head of the linked list will point to the 
longest waiting customer as we serve in first come first served manner.

We'll have a HashTable to store all the waiting users for every show. Key - show id, valud - linked hashmap containing
user id and their wait start time.

Clients can use long polling for keeping themselves updated for their reservation status.

### Reservation expiration
On the server, ActiveReservationsService keeps track of expiry of active reservations. Client will be shown a timer,
which could be a little out of sync with the server. We can add a buffer of 5 seconds on the server to safeguard from
a broken experience, such that client never times out after the server, preventing a successful purchase.

## Concurrency
How to handle situations so two users won't book the same seat. We can use transactions in SQL databases to avoid any
clashes. We can lock rows before we can update them
```sql
SET TRANSACTION  ISOLATION  LEVEL SERIALIZABLE; 
BEGIN TRANSACTION;
//do some SQL
COMMIT TRANSACTION;
```

## Fault tolerance
If ActiveReservationsService fails, we can read all the active reservations from DB since we persist reservation
status. Another option is to have a master-slave configuration so that, when the master crashes, the slave will 
take over. We're not storing waiting users in the database, so, whem WaitingUsersService failes, we don't have means
to recover the state unless we have a master-slave setup.

Similarly, we'll have a master-slave setup for databases.

## Data partitioning
If we partition on EventId, then a very hot event will cause a lot of load on that server. A better way to partion on
ShowId, this way load gets distributed evenly.

**ActiveReservationService and WaitingUsersService:** our web servers will manage all the active users' sessions and
handle all the communitcation with the users. We can use consistent hashing to allocate application servers for both
services based on the ShowID. This way all reservations and waiting users of a particular show will be handled by a
certain set of servers. Let's assume for load balancing our Consistent Hashing allocates three servers for any Show,
so whenever a reservation is expired, the server holding that reservation will do the following things:
1. Update database to remove the booking and update the seats' status.
2. Remove the reservation from the Linked HashMap
3. Notify the user that their reservation has expired
4. Broadcast a message to all WaitingUserService servers that are holding waiting users of that Show to figure out the
longest waiting user. Consistent hashing scheme will tell what servers are holding these users.
5. Send a message to the WaitingUserService server holding the longest waiting user to process their request if 
required seats have become available.

Whenever a reservation is successful, following things will happen:
1. The server holding that reservation sends a message to all servers holding the waiting users of that Show, so that
those servers can expire all the waiting users that need more seats than the available seats.
2. Upon receiving the above message, all the servers holding the waiting users will query the database to find how 
many free seats are available now. A database cache would greatly help here to run this query only once.
3. Expire all waiting users who want to reserve more seats than theavailable seats. For thi, WaitingUserService
had to iterate through the Linked HashMap of all waiting users. 