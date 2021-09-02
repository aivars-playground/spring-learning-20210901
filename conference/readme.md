use
-Dspring.profiles.active=postgresql
  or
-Dspring.profiles.active=mysql

docker create --name mysql-demo -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3306:3306 mysql:8.0.26
docker start mysql-demo
docker stop mysql-demo
docker exec -it mysql-demo bash
mysql -uroot -pmy-secret-pw
CREATE DATABASE conference_app;
CREATE USER 'app'@'172.17.0.1' IDENTIFIED BY 'not-secret-password';
GRANT ALL PRIVILEGES ON *.* TO 'app'@'172.17.0.1'

INTELLIJ: jdbc:mysql://localhost:3306/conference_app?allowPublicKeyRetrieval=true&useSSL=false
APP: jdbc:mysql://localhost:3306/conference_app