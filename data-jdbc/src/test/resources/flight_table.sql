create table flight
(
    id int auto_increment,
    origin varchar(100) not null,
    destination varchar(100) not null,
    duration int,
    constraint flight_pk primary key (id)
);
