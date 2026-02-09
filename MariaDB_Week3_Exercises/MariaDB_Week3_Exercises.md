*This assignment expands upon the MariaDB exercises we did last week, using the same `vets` database..*

First, let's get back into my container, and start up MariaDB:
![[Pasted image 20260207154450.png]]

Now that I'm back in, I can start working on the problems in this assignment.

### 1: Alias column name:

Using `AS` we can rename a column before outputting, allowing us to tweak things to a more descriptive displayed output.
![[Pasted image 20260207154812.png]]

### 2. TRIM(state) = state;

Next, we are instructed to run the query `SELECT COUNT(*) FROM vets WHERE TRIM(state) = state;`, then observe and explain the output.

We can see there are 83 rows that match the query: 
![[Pasted image 20260208112620.png]]

To get a better idea of what is contained in these rows, I'm going to tweak the query to display the first five rows matching `TRIM(state) = state `rather than the count. 

I believe this should show five rows where the data in the state column is the same before and after trimming, meaning it should contain no whitespace.

![[Pasted image 20260208112804.png]]
Interesting, we see only blank entries. From this output, I can come to the conclusion that the 83 rows where TRIM(state) = state are rows where there are no surrounding whitespaces on the entries. I reran the query without `LIMIT 5`, and all of the 83 rows are just empty data. 

This means only empty rows have no surrounding white space on the state data.

### 3. Delete empty rows

The next step of the assignment confirms my conclusion: 
	*The original CSV file had blank lines, so they were read in as empty rows. This is one thing you should  
	be aware of when reading in CSV files.*

Now, we're tasked to use a DELETE statement to clear out all the empty rows in the vets table.

![[Pasted image 20260208122252.png]]

Great! We can see our 83 empty rows have been deleted. 

### 4. Trim whitespace on states

Now that we've deleted any empty rows, we should properly trim all of our values in the state column to remove any extra whitespace.

To do this, we'll use an `UPDATE` statement.

![[Pasted image 20260209112055.png]]![[Pasted image 20260209112131.png]]

Looks good. Let's check our changes:

![[Pasted image 20260209112252.png]]

Now, the same query that proved we had extra whitespace on all of our state columns is showing us that we've properly trimmed all of the entries of the state column.

### 5. Trim whitespace on everything else

We've seen how to trim leading and trailing whitespace in one column, now let's do that for each other column. I'll alter the `UPDATE` sequence and re-run for each column.

The following query will test to make sure each of the columns was properly trimmed. We'll also use a `LIKE` statement to display anybody whose first name starts with GUADALUPE, and is followed by anything else.
![[Pasted image 20260209113445.png]]

Looks good!

### 6. Find towns starting with `LOS` 

Our next task is to find how many towns in the data start with `LOS`.

![[Pasted image 20260209114650.png]]

Looks like there are 586 towns in the data that start with `LOS`.

### 7. How many are NOT `LOS ANGELES`? 

To find out how many of the 586 towns are Los Angeles, I'll just run a `SELECT` statement to find where the town matches `LOS ANGELES` exactly, rather than LOS%. 

![[Pasted image 20260209123938.png]]

We can see 538 of the towns starting with `LOS` are `LOS ANGELES`. 
	586 - 538 = 48
So, 48 towns are not Los Angeles, and 538 are Los Angeles.

### 8.  Los - Angeles: GROUP BY

We want a count by state of people who are not from Los Angeles, but are from other towns starting with 'LOS'. 

First, let's show a count by state of all people from town starting with 'LOS'. 

![[Pasted image 20260209130449.png]]

Now, let's exclude Los Angeles.

![[Pasted image 20260209130612.png]]

Perfect! 

### 9. List of Vets from GUAM (GU) ordered by last name

Now, we'll use an `ORDER BY` clause to order our output display:

![[Pasted image 20260209131018.png]]

I've limited the output to 5 rows, but we can see the rows are sorted by last name in ascending order.

### 10. Create new table States


