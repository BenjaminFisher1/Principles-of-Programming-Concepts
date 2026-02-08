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