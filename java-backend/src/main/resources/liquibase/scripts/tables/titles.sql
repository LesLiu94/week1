CREATE TABLE employeesSchema.titles (
	title_no INT primary key,
	emp_no INT references employeesSchema.employees(emp_no) ON UPDATE CASCADE ON DELETE RESTRICT,
	title employeesSchema.employee_title NOT NULL,
	from_date date NOT NULL,
	to_date date
);

CREATE INDEX emp_no_titles_index ON employeesSchema.titles(emp_no);