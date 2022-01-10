create database if not exists ds;
use ds;

create table if not exists users (
    id int(10) not null auto_increment,
    username varchar(50) not null,
    password varchar(255) not null,
    email varchar(50) not null,
    enabled boolean not null,
    primary key (id),
    unique key uk_users_username (username),
    index index_users_username (username)
)Engine=InnoDB;

create table if not exists applications (
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
)Engine=InnoDB;

create table if not exists validations select * from applications;

create table if not exists authorities (
    username varchar(50) not null,
    authority varchar(20) not null,
    unique key uk_authorities_usr_auth (username,authority),
    foreign key (username) references users (username)
    on update cascade
    on delete cascade
)Engine=InnoDB;
