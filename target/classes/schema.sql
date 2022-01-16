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
);

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
    photo mediumblob not null,
    validated varchar(5),
    confirmed varchar(5),
    primary key (id),
    foreign key (id) references users(id)
);

create table if not exists authorities (
    id int(10) not null,
    name varchar(50) not null,
    authority varchar(20) not null,
    primary key (id),
    foreign key (id) references  users (id)
);