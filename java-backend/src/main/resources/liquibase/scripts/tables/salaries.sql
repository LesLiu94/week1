CREATE TABLE employeesSchema.salaries (
	salary_no INT primary key,
	emp_no INT references employeesSchema.employees(emp_no) ON UPDATE CASCADE ON DELETE RESTRICT,
	salary numeric(11,2) NOT NULL,
	from_date date NOT NULL,
	to_date date NOT NULL,
	active boolean NOT NULL
);

CREATE INDEX emp_no_salaries_index ON employeesSchema.salaries(emp_no);