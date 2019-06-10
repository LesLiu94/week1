insert into employeesSchema.departments (dept_no, dept_name) values
	('0000', 'Production and Operations'),
	('0001', 'Research and Development'),
	('0002', 'Purchasing'),
	('0003', 'Marketing'),
	('0004', 'Human Resources'),
	('0005', 'Accounting and Finance');

insert into employeesSchema.employees (emp_no, birth_date, first_name, last_name, gender, hire_date) values
	(94918494, '1983-10-13', 'Tim', 'Kenneford', 'M', '2017-09-24'),
	(81182092, '1966-01-19', 'Leeland', 'Pitchford', 'M', '2017-07-18'),
	(37048544, '1962-02-26', 'Fredelia', 'Dunnett', 'F', '2006-06-26'),
	(77849013, '1963-10-28', 'Jacky', 'Gisby', 'M', '2016-06-12'),
	(4078123, '1972-11-01', 'Roslyn', 'Parramore', 'F', '2003-01-23'),
	(88752158, '1990-02-16', 'Tim', 'Hans', 'F', '2008-03-31'),
	(63178566, '1960-01-12', 'Erich', 'Deshorts', 'M', '2002-05-15'),
	(31813270, '1977-05-13', 'Benetta', 'Trayford', 'F', '2017-08-04'),
	(43093583, '1978-10-05', 'Jaquelyn', 'Handyside', 'F', '2001-02-24'),
	(69760491, '1984-02-22', 'Winifred', 'O''Murtagh', 'F', '2004-01-14'),
	(13185855, '1972-09-18', 'Erich', 'Itzcak', 'M', '2017-08-13'),
	(35123415, '1994-09-03', 'Bria', 'Lattimore', 'F', '2012-07-24'),
	(48038806, '1966-04-29', 'Griz', 'Fladgate', 'M', '2009-10-04'),
	(35310144, '1993-01-26', 'Peirce', 'Guillart', 'M', '2010-03-21'),
	(14031073, '1965-02-22', 'Orren', 'Dahlback', 'M', '2017-02-07');

insert into employeesSchema.dept_emp (emp_no, dept_no, from_date, to_date) values 
	(14031073, '0005', '2018-10-10', '2015-04-07'),
	(35310144, '0002', '2018-12-23', '2015-11-13'),
	(48038806, '0004', '2018-08-20', '2005-01-12'),
	(35123415, '0003', '2018-08-31', '2018-11-18'),
	(13185855, '0000', '2018-12-14', '2015-06-20'),
	(69760491, '0003', '2018-04-04', '2008-02-19'),
	(43093583, '0005', '2018-06-03', '2010-05-08'),
	(31813270, '0000', '2018-06-17', '2001-12-07'),
	(63178566, '0001', '2018-03-18', '2012-03-15'),
	(88752158, '0002', '2018-03-09', '2012-07-23'),
	(4078123, '0002', '2018-12-26', '2007-12-29'),
	(77849013, '0001', '2018-03-11', '2007-08-26'),
	(37048544, '0004', '2018-12-04', '2003-12-03'),
	(81182092, '0003', '2018-11-28', '2014-09-28'),
	(94918494, '0004', '2018-04-30', '2015-10-24');

insert into employeesSchema.dept_manager (dept_no, emp_no, from_date, to_date) values 
	--('0005', 14031073, '2018-12-01', '2019-01-15'),
	--('0002', 35310144, '2018-03-01', '2019-02-02'),
	--('0004', 48038806, '2018-12-22', '2019-01-26'),
	--('0003', 69760491, '2018-12-26', '2019-02-28'),
	--('0000', 31813270, '2018-08-09', '2019-02-15'),
	--('0001', 63178566, '2018-03-20', '2019-02-02'),
	('0005', 14031073, '2019-01-01', '2019-11-15'),
	('0002', 35310144, '2019-03-01', '2019-12-02'),
	('0004', 48038806, '2019-02-22', '2019-11-26'),
	('0003', 69760491, '2019-02-26', '2019-12-28'),
	('0000', 31813270, '2019-05-09', '2019-12-15'),
	('0001', 63178566, '2019-04-20', '2019-12-02');

insert into employeesSchema.salaries (emp_no, salary, from_date, to_date) values 
	(14031073, 89153.07, '2017-06-10', '2019-01-04'),
	(35310144, 93853.32, '2017-06-07', '2019-01-17'),
	(48038806, 93378.26, '2017-07-08', '2019-01-23'),
	(35123415, 73116.56, '2017-12-04', '2019-01-31'),
	(13185855, 73486.75, '2017-06-19', '2019-01-09'),
	(69760491, 92887.32, '2017-11-17', '2019-01-12'),
	(43093583, 62292.86, '2017-11-23', '2019-01-15'),
	(31813270, 89746.32, '2017-12-28', '2019-01-24'),
	(63178566, 91666.12, '2017-10-21', '2019-02-01'),
	(88752158, 72361.69, '2017-02-26', '2019-02-05'),
	(4078123, 51496.38, '2017-10-02', '2019-02-16'),
	(77849013, 52350.53, '2017-03-12', '2019-02-27'),
	(37048544, 72422.45, '2017-12-09', '2019-02-28'),
	(81182092, 61291.82, '2017-12-06', '2019-02-02'),
	(94918494, 73993.01, '2017-10-16', '2019-02-28'),
	(14031073, 79153.07, '2018-03-30', '2019-09-04'),
	(35310144, 103853.32, '2018-03-17', '2019-10-17'),
	(48038806, 83378.26, '2018-05-18', '2019-11-23'),
	(35123415, 63116.56, '2018-05-24', '2019-12-31'),
	(13185855, 83486.75, '2018-06-09', '2019-10-09'),
	(69760491, 102887.32, '2018-01-27', '2019-11-12'),
	(43093583, 72292.86, '2018-01-13', '2019-12-15'),
	(31813270, 79746.32, '2018-02-08', '2019-09-24'),
	(63178566, 81666.12, '2018-02-01', '2019-12-01'),
	(88752158, 82361.69, '2018-03-06', '2019-12-05'),
	(4078123, 61496.38, '2018-04-12', '2019-12-16'),
	(77849013, 42350.53, '2018-10-22', '2019-10-27'),
	(37048544, 82422.45, '2018-02-19', '2019-10-28'),
	(81182092, 71291.82, '2018-02-16', '2019-11-02'),
	(94918494, 53993.01, '2018-01-26', '2019-11-28');

insert into employeesSchema.titles (emp_no, title, from_date, to_date) values 
	(81182092, 'Employee', '2018-12-13', '2019-12-01'),
	(94918494, 'Employee', '2018-12-05', '2019-11-07'),
	(37048544, 'Employee', '2018-12-23', '2019-11-26'),
	(77849013, 'Janitor', '2018-12-04', null),
	(4078123, 'Employee', '2018-12-09', '2019-11-17'),
	(88752158, 'Employee', '2018-12-01', '2019-11-28'),
	(63178566, 'Manager', '2018-12-07', '2019-12-17'),
	(31813270, 'Manager', '2018-12-29', '2019-11-11'),
	(43093583, 'Employee', '2018-12-28', '2019-12-04'),
	(69760491, 'Manager', '2018-12-19', null),
	(13185855, 'Employee', '2018-12-03', null),
	(35123415, 'Employee', '2018-12-19', '2019-12-11'),
	(48038806, 'Manager', '2018-12-03', '2019-11-09'),
	(35310144, 'Manager', '2018-12-13', '2019-11-12'),
	(14031073, 'Manager', '2018-12-13', '2019-12-19');
