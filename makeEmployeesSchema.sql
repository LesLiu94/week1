drop schema if exists employeesSchema cascade;
create schema employeesSchema;

do $$ begin
if not exists (select 1 from pg_catalog.pg_type where typname = 'sex') then
	create type sex as enum ('M','F');
end if;
end $$;

create table employeesSchema.departments (
	dept_no char(4) primary key,
	dept_name varchar(40) unique not null
);

create index dept_name on employeesSchema.departments(dept_name);

create table employeesSchema.employees (
	emp_no numeric(11) unique not null,
	birth_date date not null,
	first_name varchar(14) not null,
	last_name varchar(16) not null,
	gender sex not null,
	hire_date date not null
);

create table employeesSchema.dept_emp (
	emp_no numeric(11) references employeesSchema.employees(emp_no) on update cascade on delete restrict,
	dept_no char(4) references employeesSchema.departments(dept_no) on update cascade on delete restrict,
	from_date date not null,
	to_date date not null,			--not sure why this cannot be null
	primary key (emp_no, dept_no)
);

create index emp_no_dept_emp_index on employeesSchema.dept_emp(emp_no);

create index dept_no_dept_emp_index on employeesSchema.dept_emp(dept_no);

create table employeesSchema.dept_manager (
	dept_no char(4) references employeesSchema.departments(dept_no) on update cascade on delete restrict,
	emp_no numeric(11) references employeesSchema.employees(emp_no) on update cascade on delete restrict,
	from_date date not null,
	to_date date not null,			--not sure why this cannot be null
	primary key (emp_no, dept_no)
);

create index emp_no_dept_manager_index on employeesSchema.dept_manager(emp_no);

create index dept_no_dept_manager_index on employeesSchema.dept_manager(dept_no);

create table employeesSchema.salaries (
	emp_no numeric(11) primary key references employeesSchema.employees(emp_no) on update cascade on delete restrict,
	salary numeric(11) not null,
	from_date date not null,
	to_date date not null
);

create index emp_no_salaries_index on employeesSchema.salaries(emp_no);

create table employeesSchema.titles (
	emp_no numeric(11) primary key references employeesSchema.employees(emp_no) on update cascade on delete restrict,
	title varchar(50) not null,
	from_date date not null,
	to_date date
);

create index emp_no_titles_index on employeesSchema.titles(emp_no);