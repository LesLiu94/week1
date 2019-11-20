drop schema if exists employeesSchema cascade;
create schema employeesSchema;

do $$ begin
if not exists (select 1 from pg_catalog.pg_type where typname = 'sex') then
	create type employeesSchema.sex as enum ('M','F');
end if;
end $$;

do $$ begin
if not exists (select 1 from pg_catalog.pg_type where typname = 'employee_title') then
	create type employeesSchema.employee_title as enum ('EMPLOYEE','MANAGER', 'JANITOR', 'NONE');
end if;
end $$;

create table employeesSchema.departments (
	dept_no char(4) primary key,
	dept_name varchar(40) unique not null
);

create table employeesSchema.employees (
	emp_no INT primary key,
	birth_date date not null,
	first_name varchar(14) not null,
	last_name varchar(16) not null,
	gender employeesSchema.sex not null,
	hire_date date not null
);

create table employeesSchema.dept_emp (
	emp_no INT references employeesSchema.employees(emp_no) on update cascade on delete restrict,
	dept_no char(4) references employeesSchema.departments(dept_no) on update cascade on delete restrict,
	from_date date not null,
	to_date date not null,
	primary key (emp_no, dept_no)
);

create index emp_no_dept_emp_index on employeesSchema.dept_emp(emp_no);

create index dept_no_dept_emp_index on employeesSchema.dept_emp(dept_no);

create table employeesSchema.dept_manager (
	dept_no char(4) references employeesSchema.departments(dept_no) on update cascade on delete restrict,
	emp_no INT references employeesSchema.employees(emp_no) on update cascade on delete restrict,
	from_date date not null,
	to_date date not null,
	primary key (emp_no, dept_no)
);

create index emp_no_dept_manager_index on employeesSchema.dept_manager(emp_no);

create index dept_no_dept_manager_index on employeesSchema.dept_manager(dept_no);

create table employeesSchema.salaries (
	emp_no INT references employeesSchema.employees(emp_no) on update cascade on delete restrict,
	salary numeric(11,2) not null,
	from_date date not null,
	to_date date not null,
	primary key (emp_no, from_date)
);

create index emp_no_salaries_index on employeesSchema.salaries(emp_no);

create table employeesSchema.titles (
	emp_no INT references employeesSchema.employees(emp_no) on update cascade on delete restrict,
	title employeesSchema.employee_title not null,
	from_date date not null,
	to_date date,
	primary key (emp_no, title, from_date)
);

create index emp_no_titles_index on employeesSchema.titles(emp_no);

drop sequence if exists employeesSchema.emp_seq;
create sequence if not exists employeesSchema.emp_seq start 1 increment 50 minvalue 1;