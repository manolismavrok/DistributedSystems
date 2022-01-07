use ds;

drop table users;
create table users (
	id int(10) not null auto_increment,
    username varchar(50) not null,
    password varchar(255) not null,
    email varchar(50) not null,
    enabled tinyint(1) not null,
    primary key (id),
    unique key uk_users_username (username),
    index index_users_username (username)
);

drop table applications;
create table applications (
	id int(10) not null,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	email varchar(50) not null,
	phone_number varchar(50) not null,
	city varchar(50) not null,
	address1 varchar(10) not null,
	address2 varchar(50) not null,
	zip varchar(10) not null,
	unempl_date varchar(50) not null,
	photo varchar(50) not null,
    validated varchar(5),
    confirmed varchar(5),
	primary key (id), 
    foreign key (id) references users(id)
);

drop table validations;
create table validations select * from applications;

drop table authorities;
create table authorities (
	username varchar(50) not null,
    authority varchar(20) not null,
    unique key uk_authorities_usr_auth (username,authority),
	foreign key (username) references users (username) 
    on update cascade
    on delete cascade
);

insert into users values (0, 'myusername', '$2a$10$u7FFGoYdLQghzodp8/ZC/uhgC2FwsCU404ZiikBijy3aUKA0azeAG', 'manolismavrok@gmail.com', 1);
insert into users values (0, 'yourusername', '$2a$10$bp8hHYu6M/thAdc2wtIiBeXcmZvMPv1D9gG2wxHX7806gFVHh8KDy', 'asimeniah@oaed.com', 1);
insert into users values (0, 'hisusername', '$2a$10$uj2/eisi0h08caVqNvXmDOSLiQMWt14s3iqq/MF4a7iPJp4XR1T0m', 'john@oasa.com', 1);
insert into users values (0, 'admin', '$2a$10$X2.WTmLxvw0vN7SiAL7AWerQLUUEBPz6MJ5//daPUPSg8PJnaTsT.', 'group20@gmail.com', 1);


insert into authorities values ('myusername', 'ROLE_CIVILIAN');
insert into authorities values ('yourusername', 'ROLE_OAED');
insert into authorities values ('hisusername', 'ROLE_OASA');
insert into authorities values ('admin', 'ROLE_ADMIN');
insert into authorities values ('ourusername', 'ROLE_OASA');

select * from users;
select * from applications;
select * from validations;
select * from authorities;