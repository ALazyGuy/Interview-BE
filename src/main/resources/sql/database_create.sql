create database if not exists sensors;

create table if not exists `users`
(
    id bigint primary key auto_increment,
    login varchar(20) not null unique,
    password varchar(255) not null,
    role int not null
);