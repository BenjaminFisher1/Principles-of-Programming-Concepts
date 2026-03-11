This week's exercises aim to prepare us for our midterm exam tomorrow.

#### 0. Source Populate_Database_mynewpaltz.sql
I've sourced the file, let's examine the tables we've got:

CourseTable:
![[Pasted image 20260304133709.png]]


StudentTable:
![[Pasted image 20260304133950.png]]

EnrollmentTable:
![[Pasted image 20260304134023.png]]

#### 1. List students by name and ssn who are taking 5 courses

To do this, I'll create a view `fiveOrMore` representing all of the ssns from EnrollmentTable that are taking 5 or more classes. 

```sql
CREATE OR REPLACE VIEW fiveOrMore AS SELECT COUNT(*) AS numCourses, ssn FROM EnrollmentTable GROUP BY ssn HAVING numCourses > 4;
```

fiveOrMore:

```sql
SELECT * FROM fiveOrMore;
+------------+-----------+
| numCourses | ssn       |
+------------+-----------+
|          5 | 100000041 |
|          5 | 100000042 |
|          5 | 100000043 |
|          5 | 100000044 |
|          5 | 100000045 |
|          5 | 100000047 |
|          5 | 100000048 |
|          5 | 100000049 |
|          5 | 100000050 |
|          5 | 100000052 |
|          5 | 100000053 |
|          5 | 100000054 |
|          5 | 100000055 |
|          5 | 100000056 |
|          5 | 100000058 |
|          5 | 100000059 |
|          5 | 100000060 |
+------------+-----------+
```

Now, I need to join this with the StudentTable to match ssns to names.

```sql
SELECT f.numCourses, f.ssn, s.firstName FROM fiveOrMore f JOIN StudentTable s on f.ssn = s.ssn;
```

Output:
```sql
+------------+-----------+-----------+
| numCourses | ssn       | firstName |
+------------+-----------+-----------+
|          5 | 100000041 | Kevin     |
|          5 | 100000042 | Michelle  |
|          5 | 100000043 | Brian     |
|          5 | 100000044 | Carol     |
|          5 | 100000045 | Edward    |
|          5 | 100000047 | Ronald    |
|          5 | 100000048 | Melissa   |
|          5 | 100000049 | Timothy   |
|          5 | 100000050 | Deborah   |
|          5 | 100000052 | Stephanie |
|          5 | 100000053 | Jeffrey   |
|          5 | 100000054 | Rebecca   |
|          5 | 100000055 | Ryan      |
|          5 | 100000056 | Laura     |
|          5 | 100000058 | Sharon    |
|          5 | 100000059 | Gary      |
|          5 | 100000060 | Cynthia   |
+------------+-----------+-----------+
```


#### 2. List students by name and ssn who are taking 4 courses

Just alter the view:

```sql
CREATE OR REPLACE VIEW four AS SELECT COUNT(*) AS numCourses, ssn FROM EnrollmentTable GROUP BY ssn HAVI
NG numCourses = 4;
```

And the SELECT:

```sql
SELECT f.numCourses, f.ssn, s.name FROM four f JOIN StudentTable s on f.ssn = s.ssn;
```

```bash
+------------+-----------+-----------+
| numCourses | ssn       | firstName |
+------------+-----------+-----------+
|          4 | 100000046 | Amanda    |
|          4 | 100000057 | Jacob     |
+------------+-----------+-----------+
```

#### 3. List students by name and ssn who are taking 0 courses

```sql
SELECT * FROM StudentTable s WHERE s.ssn NOT IN (SELECT ssn FROM EnrollmentTable);
```

Output: 
![[Pasted image 20260304145419.png]]

#### 4. Give a count of 4 credit courses by subjectID (# of 4-credit courses in each subject).

```sql
SELECT COUNT(*), subjectId FROM CourseTable WHERE numOfCredits = 4 GROUP BY subjectId;
```
```bash
+----------+-----------+
| COUNT(*) | subjectId |
+----------+-----------+
|        2 | CS        |
|        2 | ENGR      |
|        2 | MATH      |
+----------+-----------+
```

#### 5. Give a count of 3 credit courses by subjectID

```sql
SELECT COUNT(*), subjectId FROM CourseTable WHERE numOfCredits = 3 GROUP BY subjectId;
```

```bash
+----------+-----------+
| COUNT(*) | subjectId |
+----------+-----------+
|        3 | CS        |
|        5 | ENGL      |
|        2 | ENGR      |
|        3 | MATH      |
+----------+-----------+
```

Small change.

#### 6. Are there any 5 credit courses? Are there any 1 credit courses?

```sql
MariaDB [mynewpaltz]> SELECT COUNT(*), subjectId FROM CourseTable WHERE numOfCredits = 5 GROUP BY subjectId;
Empty set (0.001 sec)

MariaDB [mynewpaltz]> SELECT COUNT(*), subjectId FROM CourseTable WHERE numOfCredits = 1 GROUP BY subjectId;
Empty set (0.001 sec)
```

No.

#### 7. List all enrolled students by name and their grades in each enrolled course

Looks like a triple join here.

First, let's join EnrollmentTable to StudentTable in a view. 

```sql
CREATE OR REPLACE VIEW es AS SELECT e.ssn,e.courseId, e.grade, s.firstName FROM EnrollmentTable e JOIN StudentTable s ON e.ssn = s.ssn GROUP BY e.ssn;
```

```bash
SELECT * FROM es;
+-----------+----------+-------+-------------+
| ssn       | courseId | grade | firstName   |
+-----------+----------+-------+-------------+
| 100000001 |        1 | A     | James       |
| 100000002 |        2 | B     | Mary        |
| 100000003 |        3 | A     | Robert      |
| 100000004 |        4 | B     | Patricia    |

etc
```

Now, let's join our view to CourseTable.

```sql
SELECT c.title, es.firstName, es.grade FROM CourseTable c JOIN es ON c.courseId = es.courseId;
```

Output:
```bash
+-----------------------------+-------------+-------+
| title                       | firstName   | grade |
+-----------------------------+-------------+-------+
| Calculus I                  | Tyler       | A     |
| Calculus I                  | Janet       | A     |
| Calculus I                  | Frank       | C     |
| Calculus I                  | Anna        | B     |
| Calculus I                  | Cynthia     | A     |
| Calculus I                  | Timothy     | A     |
| Calculus I                  | Edward      | B     |
| Calculus I                  | Kevin       | A     |
| Calculus I                  | Charles     | A     |
```

#### 8. For all courses identified by courseId of 10, list # of students enrolled, course number, & subjectId

```sql
SELECT * FROM CourseTable where CourseID = 10;
```

```bash
+----------+-----------+--------------+-------------------------+--------------+
| courseId | subjectId | courseNumber | title                   | numOfCredits |
+----------+-----------+--------------+-------------------------+--------------+
|       10 | CS        |          450 | Artificial Intelligence |            3 |
+----------+-----------+--------------+-------------------------+--------------+
```

Now that we see course with ID 10, let's get # of students enrolled. I'll use a nested SELECT:

```sql
SELECT COUNT(*) AS enrolledStu, e.courseID, a.subjectId, a.title FROM EnrollmentTable e JOIN (SELECT * FROM CourseTable WHERE CourseID = 10) AS a ON e.courseId = a.courseId;
```

output:

```bash
+-------------+----------+-----------+-------------------------+
| enrolledStu | courseID | subjectId | title                   |
+-------------+----------+-----------+-------------------------+
|          13 |       10 | CS        | Artificial Intelligence |
+-------------+----------+-----------+-------------------------+
```

#### 9. How many students are in each course (identified uniquely by courseID)?

Simple nested SELECT:

```sql
SELECT q.enrolledStu, c.title, q.courseID FROM CourseTable c JOIN (SELECT COUNT(*) AS enrolledStu, courseID
FROM EnrollmentTable GROUP BY courseID) AS q ON q.courseID = c.courseID;
```

```bash
+-------------+-----------------------------+----------+
| enrolledStu | title                       | courseID |
+-------------+-----------------------------+----------+
|          13 | Calculus I                  |        1 |
|          13 | Calculus II                 |        2 |
|          12 | Linear Algebra              |        3 |
|          14 | Probability and Statistics  |        4 |
|          13 | Abstract Algebra            |        5 |
|          11 | Intro to Computer Science   |        6 |
|          13 | Data Structures             |        7 |
|          13 | Operating Systems           |        8 |
|          12 | Database Management Systems |        9 |
etc
```

#### 10. For each student enrolled, list the first name, last name, and number of credits that student was taking.

```sql
SELECT s.firstName, s.lastName, sum(c.numOfCredits) AS TotalCreditsTaken FROM StudentTable s JOIN EnrollmentTable e ON s.ssn = e.ssn JOIN CourseTable c ON e.CourseId=c.CourseId group by s.ssn ORDER BY TotalCreditsTaken desc;
```

```bash
+-------------+------------+-------------------+
| firstName   | lastName   | TotalCreditsTaken |
+-------------+------------+-------------------+
| Michelle    | Adams      |                17 |
| Kevin       | Green      |                17 |
| Deborah     | Roberts    |                17 |
| Laura       | Parker     |                17 |
| Carol       | Baker      |                16 |
| Jeffrey     | Evans      |                16 |
| Rebecca     | Turner     |                16 |
etc
```