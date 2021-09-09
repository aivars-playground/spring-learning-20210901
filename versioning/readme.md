VERSIONING
=
Strategies:
* URI
  * path
  * request parameter
* HEADER
  * custom Media Type  (vnd - vendor specific)
  * custom header
* NO STRATEGY

Notes:
* Use single digit version, increase only for breaking changes...
* If minor changes are not breaking client, new version might not be required...
* Minor frequent version bumps might annoy client...
* Do not add multiple versions in a single app. use branches, concurrent deployments...


URI path strategy
-
Approach: different URI base paths  
Note: base path is optional...  
Solution - deploy both, use load balancer based on URI path
```properties
#application-baseV1.properties
spring.data.rest.base-path=v1
```
```shell
mvn org.springframework.boot:spring-boot-maven-plugin:run -Dspring-boot.run.profiles=baseV1
mvn org.springframework.boot:spring-boot-maven-plugin:run -Dspring-boot.run.profiles=baseV2
```
```http request
### V1
GET http://localhost:8081/v1/api/path_strategy/endpoint
### Also V1 (base path is optional)
GET http://localhost:8081/api/path_strategy/endpoint
### V2 wpuld not work on V1 service...
GET http://localhost:8081/v2/api/path_strategy/endpoint

### V2
GET http://localhost:8082/v2/api/path_strategy/endpoint
### Also V2 (base path is optional)
GET http://localhost:8082/api/path_strategy/endpoint
```

Request Parameter strategy
-
Approach: add version as request parameter
PROS: could be easy to implement
CONS: separate test setup, possible overlap of business-specific parameters, difficult routing
```http request
### V1
GET http://localhost:8080/api/parameter_strategy/endpoint?version=v1

### V2
GET http://localhost:8080/api/parameter_strategy/endpoint?version=v2
```

Media type strategy
-
Approach: use vnd (vendor specific) Media Types
```java
@GetMapping(value = "/endpoint", produces = "application/vnd.example.api.v1+json")
```
```http request
### V1
GET http://localhost:8080/api/custom_media/endpoint
Accept: application/vnd.example.api.v1+json

# returns
# HTTP/1.1 200 
# Content-Type: application/vnd.example.api.v1+json

### V2
GET http://localhost:8080/api/custom_media/endpoint
Accept: application/vnd.example.api.v2+json
```

Custom Header strategy
-
Approach: define custom header  
```java
@GetMapping(value = "/endpoint", headers =  "Accept-version=v1")
```
```http request
###
GET http://localhost:8080/api/custom_header/endpoint
Accept-version:v1

###
GET http://localhost:8080/api/custom_header/endpoint
Accept-version:v2
```

No strategy
-
Approach: define new name
```http request
### V1
GET http://localhost:8080/api/no_strategy/endpoint

### V2
GET http://localhost:8080/api/no_strategy/new_endpoint
```
