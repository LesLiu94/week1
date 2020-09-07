CREATE TABLE employeesSchema.authorities(
    username varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,
    constraint fk_authorities_users foreign key(username) references employeesSchema.users(username)
);
CREATE unique index ix_auth_username ON employeesSchema.authorities(username,authority);