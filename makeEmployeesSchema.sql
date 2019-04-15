do $$ begin

drop schema if exists 'employeesdb' cascade
create schema 'employeesdb'
	
if not exists (select 1 from pg_catalog.pg_type where typname = 'sex') then
	create type sex as enum ('M','F');
end if;

create table departments (
	dept_no char(4) primary key,
	dept_name varchar(40) unique not null
);

create table employees (
	emp_no integer unique not null,		--shows primary key symbol but not listed under "Indexes"
	birth_date date not null,
	first_name varchar(14) not null,
	last_name varchar(16) not null,
	gender emp_gender not null,
	hire_date date not null
);

create table dept_emp (
	emp_no integer references employees(emp_no) on update cascade on delete restrict,
	dept_no char(4) references departments(dept_no) on update cascade on delete restrict,
	from_date date not null,
	to_date date not null,			--not sure why this cannot be null
	primary key (emp_no, dept_no)
);

create table dept_manager (
	dept_no char(4) references departments(dept_no) on update cascade on delete restrict,
	emp_no integer references employees(emp_no) on update cascade on delete restrict,
	from_date date not null,
	to_date date not null,			--not sure why this cannot be null
	primary key (emp_no, dept_no)
);

create table salaries (
	emp_no integer primary key references employees(emp_no) on update cascade on delete restrict,
	salary integer not null,
	from_date date not null, 		--shows primary key symbol but not listed under "Indexes"
	to_date date not null
);

create table titles (
	emp_no integer primary key references employees(emp_no) on update cascade on delete restrict,
	title varchar(50) not null,		--shows primary key symbol but not listed under "Indexes"
	from_date date not null,		--shows primary key symbol but not listed under "Indexes"
	to_date date
);

end $$;