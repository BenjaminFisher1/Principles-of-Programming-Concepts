For this week's exercises, we are tasked to load a file into a database called 'employees'.

Let's log in as root and create a new database: 
![[Pasted image 20260225142226.png]]
Next, let's give my user permissions.![[Screenshot From 2026-02-25 14-23-37.png]]

Now, I'll login with my user b. 

![[Pasted image 20260225142557.png]]
Looks good! 

Next step is to read in the dump file:
![[Pasted image 20260225143213.png]]

And then test that the file import succeeded:
![[Pasted image 20260225143315.png]]
And let's check out both tables to see what we're working with: 

`departments:` 
![[Pasted image 20260225144023.png]]

`employees:`
![[Pasted image 20260225144115.png]]

Great! Now we can get started on our exercises.

#### 1. List employees and their managers

A simple inner join can solve this:
```sql
SELECT e.name, a.name as manager FROM employees e INNER JOIN employees a ON e.manager_id = a.id;
```

![[Pasted image 20260225144416.png]]

#### 2. List departments and their managers

Simple left join:
```sql
SELECT d.department_name, e.name as manager FROM departments d LEFT JOIN employees e ON d.manager_id = e.id;
```

![[Pasted image 20260225145120.png]]

#### 3. List department each person works in

Left join again:
```sql
SELECT e.name, d.department_name as dept FROM employees e LEFT JOIN departments d ON e.department_id = d.id;
```

![[Pasted image 20260225145323.png]]

#### 4. Find employees who have no manager

Since we know an employee with no manager has `manager_id` NULL, we can use `IS NULL`:

```sql
SELECT e.name FROM employees e WHERE manager_id IS NULL;
```

![[Pasted image 20260225145712.png]]

#### 5. Find employees who have no departments

Similar idea as #4, using `IS NULL`. 

```sql
SELECT e.name FROM employees e WHERE department_id IS NULL;
```

![[Pasted image 20260225150726.png]]

#### 6. Show a count of employees in each department, including those who have no departments.

First, I'll run a query to show all employees and their departments, even if they have no department.

```sql
SELECT e.id, e.name, d.department_name FROM employees e LEFT JOIN departments d ON e.department_id = d.id;
```

![[Pasted image 20260225152219.png]]

Now, let's alter it to give us the count per department and group by department name.

```sql
SELECT COUNT(*), d.department_name FROM employees e LEFT JOIN departments d ON e.department_id = d.id GROUP BY d.department_name;
```

![[Pasted image 20260225152533.png]]

Looks great!

#### 7. List name, salary, and department name of managers (those who have a department but no manager) 

For this question, we are looking for people from `employees` who have no manager_id, meaning they are managers themselves. We'll combine `IS NULL` and a left join here:

```sql
SELECT e.name, e.salary, d.department_name FROM employees e LEFT JOIN departments d ON e.department_id =
d.id  WHERE e.manager_id IS NULL;
```

![[Pasted image 20260225153129.png]]

Looks good! Interesting note: there are people with no department **or** manager. 


#### 8. List employees whose salaries are higher than their manager's salaries.

For this, we need to self join the employees table to compare salaries of employees and their managers:

```sql
SELECT e.name, e.salary, a.name as manager, a.salary as manager_salary FROM employees e LEFT JOIN employees a ON e.manager_id = a.id WHERE e.salary > a.salary;
```


![[Pasted image 20260225154247.png]]

It looks like there aren't many employees with salaries greater than their manager's salary.
