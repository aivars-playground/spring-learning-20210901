VERSIONING
=
Strategies:
* URI
* Request Parameter
* Media type
* Custom Header
* No strategy

URI strategy
-
Approach: different URIs  
PROS: invalidates cache since urls are different  
CONS: need two separate test setups
```java
@RestController()
@RequestMapping("api/v1/url_strategy")
class Controller1 {}

@RestController()
@RequestMapping("api/v2/url_strategy")
class Controller2 {}
```
```http request
###
GET http://localhost:8080/api/v1/url_strategy/endpoint

### V2
GET http://localhost:8080/api/v1/url_strategy/endpoint
```

Request Parameter strategy
-
Approach: add version as request parameter
PROS: could be easy to implement
CONS: separate test setup, possible overlap of business-specific parameters, difficult routing
```http request
### V1
GET http://localhost:8080/api/parameter_strategy/endpoint?version=1

### V2
GET http://localhost:8080/api/parameter_strategy/endpoint?version=2
```

Media type strategy
-
Approach: use vnd (vendor specific) Media Types  
PROS: one endpoint  
CONS: non-standard (possibly, confusing) Media Type
```http request
### V1
GET http://localhost:8080/api/media_strategy/endpoint
Accept: application/vnd.example.v1+json

### V2
GET http://localhost:8080/api/media_strategy/endpoint
Accept: application/vnd.example.v2+json
```

Custom Header strategy
-
Approach: define custom header  
PROS: one endpoint
```http request
### V1
GET http://localhost:8080/api/cust_header_strategy/endpoint
My-Custom-API-Version-Header: v1

### V2
GET http://localhost:8080/api/media_strategy/endpoint
My-Custom-API-Version-Header: v2
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
