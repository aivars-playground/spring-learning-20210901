Prepare environment:
=
```shell
docker create --name mysql-demo -e MYSQL_ROOT_PASSWORD=toor -p 3306:3306 mysql:latest
docker start mysql-demo
docker exec -it mysql-demo bash mysql -uroot -ptoor
    > CREATE DATABASE demo; 
    > CREATE USER 'data-jdbc'@'172.17.0.1' IDENTIFIED BY 'secret'; 
    > USE demo;
    > GRANT ALL PRIVILEGES ON demo TO 'data-jdbc'@'172.17.0.1'
    
docker stop mysql-demo
```
```shell
jdbc:mysql://localhost:3306/demo
```
```http request
GET http://localhost:8080/database
```



