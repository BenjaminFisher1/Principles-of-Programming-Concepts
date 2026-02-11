This assignment is a bit shorter than the past few. Let's get started:

*1. If you haven’t already done so, make sure you have both the vets table in your database.  
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