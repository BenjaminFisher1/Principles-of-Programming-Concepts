
We are given a print out for this lab, part of it is database creation and population, and the next part is to write a java program to interact with it.

### Database:
*Create a mariadb database called labwork on your machine*

![[Pasted image 20260318142540.png]]

Next, I've granted my user all privileges. Now let's log in.

![[Pasted image 20260318142800.png]]

Looks good!

Next step is to create 3 tables, which I'll show the schema for after creation.

#### customer:
```
MariaDB [labwork]> CREATE TABLE customer(
    -> customer_id int(11) PRIMARY KEY,
    -> customer_name VARCHAR(100),
    -> city VARCHAR(100),
    -> grade int(11),
    -> salesman_id int(11),
    FOREIGN KEY (salesman_id) REFERENCES salesman(salesman_id));
```

#### orders:
```
MariaDB [labwork]> CREATE TABLE orders(
    -> order_no int(11),
    -> purchase_amt int(11),
    -> order_date DATE,
    -> customer_id int(11),
    -> salesman_id int(11)
	    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
	    FOREIGN KEY (salesman_id) REFERENCES salesman(salesman_id));
```

#### salesman:
```
MariaDB [labwork]> CREATE TABLE salesman(
    -> salesman_id int(11) PRIMARY KEY,
    -> name VARCHAR(100),
    -> city VARCHAR(100),
    -> commission DOUBLE);
```

- *Grade is int ranging from 100 to 500 assigned to customer based on purchase characteristics. *
- *Commission is a decimal value for percentage commission the salesman gets (i.e. commission = .15 means a 15% commission.*


Full tables desc:
```
MariaDB [labwork]> DESCRIBE customer;
+---------------+--------------+------+-----+---------+-------+
| Field         | Type         | Null | Key | Default | Extra |
+---------------+--------------+------+-----+---------+-------+
| customer_id   | int(11)      | NO   | PRI | NULL    |       |
| customer_name | varchar(100) | YES  |     | NULL    |       |
| city          | varchar(100) | YES  |     | NULL    |       |
| grade         | int(11)      | YES  |     | NULL    |       |
| salesman_id   | int(11)      | YES  | MUL | NULL    |       |
+---------------+--------------+------+-----+---------+-------+
5 rows in set (0.001 sec)

MariaDB [labwork]> DESCRIBE orders;
+--------------+---------+------+-----+---------+-------+
| Field        | Type    | Null | Key | Default | Extra |
+--------------+---------+------+-----+---------+-------+
| order_no     | int(11) | NO   | PRI | NULL    |       |
| purchase_amt | int(11) | YES  |     | NULL    |       |
| order_date   | date    | YES  |     | NULL    |       |
| customer_id  | int(11) | YES  | MUL | NULL    |       |
| salesman_id  | int(11) | YES  | MUL | NULL    |       |
+--------------+---------+------+-----+---------+-------+
5 rows in set (0.001 sec)

MariaDB [labwork]> DESCRIBE salesman;
+-------------+--------------+------+-----+---------+-------+
| Field       | Type         | Null | Key | Default | Extra |
+-------------+--------------+------+-----+---------+-------+
| salesman_id | int(11)      | NO   | PRI | NULL    |       |
| name        | varchar(100) | YES  |     | NULL    |       |
| city        | varchar(100) | YES  |     | NULL    |       |
| commission  | double       | YES  |     | NULL    |       |
+-------------+--------------+------+-----+---------+-------+
4 rows in set (0.001 sec)
```


Next, we are instructed to use AI to generate data for the tables.
Used Claude to generate the following insert statements:

```sql
INSERT INTO salesman VALUES
(1001, 'Alice Johnson', 'New York',    0.15),
(1002, 'Bob Smith',     'Los Angeles', 0.12),
(1003, 'Carol White',   'Chicago',     0.18),
(1004, 'David Brown',   'Houston',     0.10),
(1005, 'Eva Green',     'Phoenix',     0.20);
```

```sql
INSERT INTO customer VALUES
(2001, 'Tom Harris',    'New York',     300, 1001),
(2002, 'Sara Lee',      'Boston',       500, 1001),
(2003, 'Mike Davis',    'Los Angeles',  200, 1002),
(2004, 'Nancy Kim',     'San Diego',    400, 1002),
(2005, 'James Wilson',  'Chicago',      100, 1003),
(2006, 'Linda Moore',   'Houston',      300, 1004),
(2007, 'Chris Taylor',  'Dallas',       500, 1005),
(2008, 'Maria Garcia',  'Phoenix',      200, 1005);
```

```sql
INSERT INTO orders VALUES
(3001,  150, '2024-01-15', 2005, 1003),  -- grade 100
(3002,  350, '2024-01-22', 2003, 1002),  -- grade 200
(3003,  750, '2024-02-03', 2001, 1001),  -- grade 300
(3004, 1200, '2024-02-14', 2004, 1002),  -- grade 400
(3005, 2500, '2024-02-28', 2002, 1001),  -- grade 500
(3006,  480, '2024-03-05', 2008, 1005),  -- grade 200
(3007, 3100, '2024-03-12', 2007, 1005),  -- grade 500
(3008,  620, '2024-03-18', 2006, 1004),  -- grade 300
(3009,   90, '2024-04-02', 2005, 1003),  -- grade 100
(3010, 1800, '2024-04-10', 2004, 1002),  -- grade 400
(3011, 2800, '2024-04-22', 2002, 1001),  -- grade 500
(3012,  900, '2024-05-01', 2001, 1001);  -- grade 300
```


Now that I have some mock data, I can start the java program.

### DBConnection.java

This program should take username, password, and database name as cmd line arguments, then connect to the labwork database. Then, it should retrieve data and create a `Sales` object for each order number, putting each object into an` ArrayList<Sales>. `

The sales class is:

class Sales {
	int orderNumber;
	String customerName;
	String customerCity;
	String salesmanName;
	double amount;
	double commissionAmount;
	//commissionAmount is amount*commission
	//also add a constructor
}

Finished the program!

to run:
```
java -cp .:mariadb-java-client-3.5.7.jar DBConnection
```

```📦[b@ubuntu MariaDB_Week9_Exercises]$ javac DBConnection.java 
📦[b@ubuntu MariaDB_Week9_Exercises]$ java -cp .:mariadb-java-client-3.5.7.jar DBConnection 
Enter your username: b
Enter your password: XXXXXXXXXXXXXX.
Enter db name:
labwork
Connected to MariaDB!
Grabbing Sales.
3003 Tom Harris New York Alice Johnson 750.0 112.5
3005 Sara Lee Boston Alice Johnson 2500.0 375.0
3011 Sara Lee Boston Alice Johnson 2800.0 420.0
3012 Tom Harris New York Alice Johnson 900.0 135.0
3002 Mike Davis Los Angeles Bob Smith 350.0 42.0
3004 Nancy Kim San Diego Bob Smith 1200.0 144.0
3010 Nancy Kim San Diego Bob Smith 1800.0 216.0
3001 James Wilson Chicago Carol White 150.0 27.0
3009 James Wilson Chicago Carol White 90.0 16.2
3008 Linda Moore Houston David Brown 620.0 62.0
3006 Maria Garcia Phoenix Eva Green 480.0 96.0
3007 Chris Taylor Dallas Eva Green 3100.0 620.0

```

This program grabs all sales and needed info, then stores each sale to a `sale` object, puts each `sale` object in an ArrayList, iterates the ArrayList, then prints each `sale` object. 

I've uploaded the program to this directory of my github as well.