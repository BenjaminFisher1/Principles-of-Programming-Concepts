This assignment is a bit shorter than the past few. Let's get started:

### 1. Read in CSV:
*If you haven’t already done so, make sure you have both the vets table in your database.  
Replace your States table, if you already have it from class, with the data in the States_Territories.csv  
file (given). This file includes Territories like GUAM, Northern Marinara Islands etc.*

I do have an existing States table. First things first, we need to replace the data in this table with the data in States_Territories.csv.

![[Pasted image 20260211142215.png]]
I truncate the table to wipe the existing data in the table.

Next, let's read our new CSV into the table.
![[Pasted image 20260211143429.png]]
Now double checking the CSV read in properly.

![[Pasted image 20260211143534.png]]

Annoyingly, the header read in. Let's clean that up:

![[Pasted image 20260211143725.png]]

Great! Our CSV is properly read in, and we can continue.

### 2. Obtain Count of Vets From Each State, Descending:
For this task, we need to combine a few of the things I've used individually into one query: `GROUP BY, ORDER BY` and `COUNT(*)`, as well as using `IN` for the first time.

![[Pasted image 20260211145343.png]]

This query grabs the state and count for any state in vets that matches an Abbreviated state in states, then groups by state and sorts the output by the count in descending order.

### 3. Obtain List of States in vets But Not States:
Pretty simple, I'll just slightly alter the previous command to contain `NOT IN` instead of `IN`.

![[Pasted image 20260211150518.png]]

This query shows all states/territories not contained in the states table, as well as their counts.

### 4. Source .sql files:

Something different now: sourcing .sql files and observing their results. First, we do *StudentExample.sql*. 

![[Pasted image 20260211151013.png]]

We can see the database changed, and we are no longer in `fisher`. Let's list the tables of our new database:

![[Pasted image 20260211151201.png]]

This new database looks like an example of data stored by a university regarding a class. 

Now, let's source *EmployeesDatabase.sql*:

![[Pasted image 20260211154818.png]]

We can see that we now have tables `departments` and `employees` in our mynewpaltz database. 

That concludes the assignment.


