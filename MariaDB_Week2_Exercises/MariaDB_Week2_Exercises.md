### Setup
Again, I'm completing this assignment on the Project Bluefin O.S. I'm going to create a new Ubuntu container for this assignment.

![[Pasted image 20260203185430.png]]

I've selected Ubuntu 24.04, because I already have the image downloaded, and it is stable. 

![[Pasted image 20260203185655.png]]

Here I've entered my container, and we can see that setup is successful. Let's enter it and get MariaDB installed.

We'll install both the server and the client for MariaDB. The MariaDB installation guide recommends installing with `galera-4`, a cluster library that allows MariaDB to run consistently across multiple nodes. Because I'm just setting up a single instance for this one assignment, I'm going to skip installing galera.

![[Pasted image 20260203190718.png]]

I've trimmed the screenshot because the output for this installation was far too long to include, but we successfully installed MariaDB client and server.

Now, let's start the server.

![[Pasted image 20260203191315.png]]

Next, we run the security script. I add a password to the root account,and disable anonymous users. After that, I create a database: 
![[Pasted image 20260203192958.png]]

Next, I need to create an ordinary user account. 

First, I'll access the root account.

![[Pasted image 20260203191744.png]]

Now, I'll make a new user and assign a password.

![[Screenshot From 2026-02-03 19-22-08.png]]

The next step is to grant my new user permissions. 

![[Pasted image 20260203193113.png]]

Okay, my user has privileges. Let's `FLUSH` to be sure our privileges are updated.

![[Pasted image 20260203193300.png]]

Now, let's exit the root account and sign into the `bf` account. I'm using the `--local-infile` flag to allow loading files from my computer to the database.

![[Pasted image 20260203194721.png]]

Continuing on, I'm tasked to create a table called `vets`, with the following fields:

```
lname varchar(100), fname varchar(100), town varchar(100), state varchar(20)
```

![[Pasted image 20260203200025.png]]

This container shares the home directory of my host machine, so I don't have to use podman to copy the CSV in. Now, we're tasked to read in the file, which contains the last name, first name, town, and state of Vietnam Veterans.

![[Pasted image 20260203220349.png]]

Great! Looks like our CSV file properly read into our `vets` table. 

Now, we have a few SQL queries to run: 

	(a) How many veterans are listed in the file?