CREATE TABLE employeesSchema.dept_emp (
	dept_emp_no INT primary key,
	emp_no INT references employeesSchema.employees(emp_no) ON UPDATE CASCADE ON DELETE RESTRICT,
	dept_no char(4) references employeesSchema.departments(dept_no) ON UPDATE CASCADE ON DELETE RESTRICT,
	from_date date NOT NULL,
	to_date date NOT NULL
);

CREATE INDEX emp_no_dept_emp_index ON employeesSchema.dept_emp(emp_no);

CREATE INDEX dept_no_dept_emp_index ON employeesSchema.dept_emp(dept_no);