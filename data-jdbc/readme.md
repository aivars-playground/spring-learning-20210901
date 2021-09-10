Prepare environment:
=
```shell
docker create --name mysql-demo -e MYSQL_ROOT_PASSWORD=toor -p 3306:3306 mysql:latest
docker start mysql-demo
docker exec -it mysql-demo bash mysql -uroot -ptoor
    > CREATE DATABASE demo; 
    > CREATE USER 'data-jdbc'@'172.17.0.1' IDENTIFIED BY 'secret'; 
    > USE demo;
    > GRANT ALL PRIVILEGES ON demo.* TO 'data-jdbc'@'172.17.0.1';
    
docker stop mysql-demo
```
```sql
create table flight
(
  id int auto_increment,
  origin varchar(100) not null,
  destination varchar(100) not null,
  duration int,
  constraint flight_pk primary key (id)
);
```
```shell
jdbc:mysql://localhost:3306/demo
```
```http request
GET http://localhost:8080/database
```

Notes
-
* `JdbcTemplate#update` does not return object id  
* `SimpleJdbcInsert#executeAndReturnKey`  can return id when configured  

