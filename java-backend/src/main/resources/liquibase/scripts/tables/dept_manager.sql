CREATE TABLE employeesSchema.dept_manager (
	dept_manager_no INT primary key,
	dept_no char(4) references employeesSchema.departments(dept_no) ON UPDATE CASCADE ON DELETE RESTRICT,
	emp_no INT references employeesSchema.employees(emp_no) ON UPDATE CASCADE ON DELETE RESTRICT,
	from_date date NOT NULL,
	to_date date NOT NULL
);

CREATE INDEX emp_no_dept_manager_index ON employeesSchema.dept_manager(emp_no);

CREATE INDEX dept_no_dept_manager_index ON employeesSchema.dept_manager(dept_no);