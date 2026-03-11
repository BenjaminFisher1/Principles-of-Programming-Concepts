Shorter exercise set for this lab. 

## Exercise on JDBC connector


*Read the document (Week 7) of Java Connector, JDBC driver. This allows a Java program to query a  
mariadb database and retrieve information.*

*Write a Java Program, DisplayCountries that allows user to put in a language name as a String and have the program display a list of countries in the nation database where that language is either an official or an unofficial language.*

First things first, I'll source the nation.sql file.

I have two containers running, one for java and one running the DB. To access the locally hosted database, in my java program I need:

```java
String url = "jdbc:mariadb://localhost:3306/nation"
```

We have to hardcode the password for the DB into this program. This is unsafe. Do not do this.

In real practice, I would pull it from a .env or a key vault. 

To show a list of countries with the user's language, we need to examine how the DB associates languages with countries:

Countries Table:

![[Pasted image 20260311151828.png]]

country_languages table:

![[Pasted image 20260311151920.png]]

languages table:

![[Pasted image 20260311152003.png]]

So we need to join languages to country_languages on language_id, then join that to countries on country_id.

Here's the first piece of the query, searching for Dutch as an example:

![[Pasted image 20260311152733.png]]

```sql
SELECT l.*, cl.country_id FROM country_languages cl JOIN languages l ON l.language_id = cl.language_id WHERE l.language='Dutch' GROUP BY cl.country_id LIMIT 5;
```


I've nested this select statement and thrown in some join logic to connect it to the countries table on the country_id.

```sql
SELECT c.name, q.language FROM countries c JO
IN (SELECT l.*, cl.country_id FROM country_languages cl JOIN languages l ON l.language_id = cl.language_id WHERE l.language='Dutch' GROUP BY cl.country_id) AS q ON q.country_id = c.country_id;
```

![[Pasted image 20260311154437.png]]

Now in actuality, we do not need to return the language column, so I'll remove that from the query.

```sql
SELECT c.name FROM countries c JO
IN (SELECT l.*, cl.country_id FROM country_languages cl JOIN languages l ON l.language_id = cl.language_id WHERE l.language='Dutch' GROUP BY cl.country_id) AS q ON q.country_id = c.country_id;
```

This will return only the country names who speak Dutch, officially or unofficially.

java program is done, need to download jdbc.


```
📦[b@ubuntu MariaDB_Week8_Exercises]$ java -cp .:mariadb-java-client-3.5.7.jar langFinder.java
Connected to MariaDB!
Please enter your language, and this program will return nations who speak that language, unoffical and official.
Dutch
Conutries which speak DutchAruba
Netherlands Antilles
Belgium
Canada
Netherlands
```