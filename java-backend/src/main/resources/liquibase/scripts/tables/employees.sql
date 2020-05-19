CREATE TABLE employeesSchema.employees (
	emp_no INT primary key,
	birth_date date NOT NULL,
	first_name varchar(14) NOT NULL,
	last_name varchar(16) NOT NULL,
	gender employeesSchema.sex NOT NULL,
	hire_date date NOT NULL
);