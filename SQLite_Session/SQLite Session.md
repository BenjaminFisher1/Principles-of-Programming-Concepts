*This assignment serves as an introductory exercise to reading csv data into a database.*

## Setup
I'm completing this assignment on the Project Bluefin O.S. I don't anticipate needing SQLite installed on my host system, so i'm going to set up a basic Ubuntu Distrobox for this assignment.

![[Pasted image 20260130164116.png]]

I've gone with Ubuntu 24.04, because it's stable and I already have the image downloaded.

![[Pasted image 20260130140050.png]]

Here I've entered my container, and we can see that setup is successful. Let's enter it and get sqlite3 installed.  

![[Pasted image 20260130144412.png]]

Alright, we've completed the installation and setup, and we can get started on the actual assignment.

## Assignment
First, we need to copy our csv into the container. 

On the host machine, i'm using podman's container manager tools to copy the csv file (nyt1.csv) into my container:
![[Pasted image 20260130150217.png]]

Now, let's check that it successfully copied:
![[Pasted image 20260130150534.png]]

Great! Our csv is on the container, and we can start reading the csv data into our sqlite database.

Let's examine our file. I'll use `head` to display the first 5 lines of the csv. 

![[Pasted image 20260130150810.png]]

We can see our csv contains 5 columns, all holding integer values: Age, Gender, impressions, clicks, and Signed_In.

Next, we need to start up the sqlite CLI.

![[Pasted image 20260130145539.png]]

Let's start by creating a table following the structure of the csv. `.mode csv` tells SQLite to expect CSV data. `.header on` warns of a header that should be skipped. Then, we import the data from nyt1.csv into the nyt1 table. SQLite reads the CSV file, adding each row to the table. Finally, we'll display the first 5 rows of our table.

![[Pasted image 20260130151157.png]]

It looks like even though I warned SQLite of the header, it got imported anyway. Let's correct that.

![[Pasted image 20260130151731.png]]

Awesome! Now we've deleted the junk row. 
Let's see how many rows we have in this table.

![[Pasted image 20260130151909.png]]

Great, we have 458,441 rows in our nyt1 table. Let's try out `AND`. 
How many rows have both signed in and had 3 impressions?

![[Pasted image 20260130152459.png]]

Let's try an `OR` statement. How about all the rows with non zero impressions or clicks?

![[Pasted image 20260130152744.png]]

Nice! We've successfully setup a SQLite database, read in a CSV file, and tested out a few basic commands. 

Let's save our work to a database file, and exit.

![[Pasted image 20260130153342.png]]

Finally, I'll make a new folder for database files in my container and move the new file there.

![[Pasted image 20260130153909.png]]

## Conclusion

This was a nice exposure to SQLite, and I look forward to learning more with SQLite in this class. 