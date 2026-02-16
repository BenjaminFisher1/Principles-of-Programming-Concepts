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

![[Pasted image 20260216132205.png]]

