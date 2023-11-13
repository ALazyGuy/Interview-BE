create database if not exists sensors;

create user if not exists sensors_user identified by 'password';
grant all privileges on sensors.* to sensors_user;
flush privileges;

use sensors;

create table if not exists `users`
(
    id       bigint primary key auto_increment,
    login    varchar(20)  not null unique,
    password varchar(255) not null,
    role     int          not null
);

create table if not exists `sensor_types`
(
    id   bigint primary key auto_increment,
    name varchar(30) unique not null
);

create table if not exists `sensor_metrics`
(
    id   bigint primary key auto_increment,
    value varchar(15) unique not null
);

create table if not exists `sensors`(
    id bigint primary key auto_increment,
    name varchar(30) unique not null,
    model varchar(15) not null ,
    `from` int,
    `to` int,
    location varchar(40),
    description varchar(200),
    sensor_type_id bigint not null,
    sensor_metric_id bigint not null,
    foreign key(sensor_metric_id) references sensor_metrics(id),
    foreign key(sensor_type_id) references sensor_types(id)
);