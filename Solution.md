# Solution for the Task

General points:

-> pricing should go before saving record to the data base
that allows reducing records in database and store only 
(client, phone, price of call for this number)


### Scenario 1.

We have the number of client small enough to store them in memory.
For example, if it's a log of the small letting agency
 and number of clients about 10^5.

In this case log file can be processed in memory, without using a database.
 There is no need to load whole file in memory, so file can be 10 Gb or more, 
 but number of clients could be small enough to be processed in memory.   

Design of the solution: store clients in memory, init HashMap<>,
 where:
  
- Key is String and responsible for the client identificator
- Value Int and responsible for the current bill and the most expensive phone session

For Promotion program, we define one more HashMap with the same Key & Value types.

The function to calculate promotion could be different on the different promotions.



### Scenario 2.

The client number bigger then the number we can store in memory. 
We're storing all clients in the database.
We need to store in the database only 2 values: ClientId and Bill.

For promotion program, we store separate column: Deduction.

```
------------+------+-----------+
| client_id | bill | deduction |
------------+------+-----------+
| A         | 56   | 5         |
------------+------+-----------+
Final Bill:
A -> 51
```

The function to promote clients can be different.

Final Bill calculated after log file is processed by analyzing bills and deductions.

Here, we can't invoke "findAll" method to load clients in memory, but we can 
provide paging of data from the table. 

