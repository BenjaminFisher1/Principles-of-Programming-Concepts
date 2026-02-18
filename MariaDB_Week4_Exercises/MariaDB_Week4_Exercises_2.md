Continuing with the previous set of exercises, this time using JOINS. 

*1. List employee names and their department names; only include employees who are currently  
assigned to a department.*

First, let's take a look at the structure of our two tables, employees and departments:

![[Pasted image 20260216124138.png]]

We can see employees has employee ids, names, salary, department_id, and manager_id.

![[Pasted image 20260216124212.png]]

And departments has id, department_name, and manager_id.

We want to list all employees currently assigned to a department. By using an `INNER JOIN`, we can select only the employees who's department id is contained in the departments table. 

```SQL
select employees.id, employees.name, departments.department_name from employees INNER JOIN departments ON
employees.department_id = departments.id;
```

![[Pasted image 20260218144436.png]]


*2. List employee names and their department names including those who are not assigned a department.*

To show this, we can simply alter the query from 1 to use a left join, outputting all employees, even if they aren't in a database. I'm also renaming `employees` to `e` and `departments` to `d`, just to compress the query.

```mysql
SELECT e.id, e.name, d.department_name FROM employees e LEFT JOIN departments d ON e.department_id = d.id;
```

![[Pasted image 20260218143706.png]]

We can see that the query is successful, outputting `NULL` for employees who do not belong to any department.

*3. List all department names and the employees assigned to them. List all departments, even if they  
have no employees assigned to them.* 

For this, we can use a left join like the one from the first problem, but placing the departments table on the left instead. 

```mysql
SELECT d.department_name, e.name FROM departments d LEFT JOIN employees e ON d.id = e.department_id;
```

![[Pasted image 20260218144232.png]]

Here we can see all departments, even those without employees. We also could have altered the statements from 1 and 2 to use a right join.

*4. List all employee names and their manager’s names. List only those employees who have a manager.*

TODO: WORKING ON:
SELECT e.name, d.manager_id FROM employees e RIGHT  JOIN departments d ON e.manager_id = d.manager_id;

*5. List employees and their department names*
