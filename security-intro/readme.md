Spring Security training
=

when enabling Spring security (spring-boot-starter-security)  
default user 'user' is created, generated pw is in log...

```properties
management.endpoint.health.show-components=when_authorized
management.endpoint.health.show-details=when_authorized
```
```http request
###
http://localhost:8080/actuator/health

###
http://localhost:8080/actuator/health
Authorization: Basic user <token from console>
```