CREATE TABLE employeesSchema.users(
    username varchar(50) NOT NULL primary key,
    password varchar(50) NOT NULL,
    enabled boolean NOT NULL
);