updates
===
exposing through http all enabled endpoints
```yaml
management.endpoints.web.exposure.include=*
```

enabling shutdown...   
```yaml
management.endpoints.web.exposure.include=shutdown
management.endpoint.shutdown.enabled=true
```
```http request
POST http://localhost:8080/actuator/shutdown
```

change logging
```yaml
management.endpoints.web.exposure.include=beans,metrics,info,health,loggers
logging.level.root=info
```
```http request
POST http://localhost:8080/actuator/loggers/root
Content-Type: application/json

{"configuredLevel": "TRACE"}
```

adding info
```
io.schultz.dustin.todoapp.customization.ProjectInfo
```
```http request
GET http://localhost:8080/actuator/info
```

ading health
```
io.schultz.dustin.todoapp.customization.CustomProjectHealth
```
```yaml
management.endpoints.web.exposure.include=health
management.endpoint.health.show-components=always
management.endpoint.health.show-details=when_authorized
```
```http request
GET http://localhost:8080/actuator/customProjectHealth
```

metrics
```http request
io.schultz.dustin.todoapp.controller.ControllerWithMetrics
```
```http request
GET http://localhost:8080/metered/lists
```

custom actuator
```
io.schultz.dustin.todoapp.customization.MyCustomActuator
```
```http request
###
GET http://localhost:8080/actuator/mycustomactuator
###
DELETE http://localhost:8080/actuator/mycustomactuator
###
POST http://localhost:8080/actuator/mycustomactuator
Content-Type: application/json

{"status":"CHANGED"}
```


# todo-app

This repository contains a simple todo application built with Spring Boot

## Build

Build with the included Maven wrapper

    ./mvnw clean install

## Usage

Run via command line

    ./mvnw spring-boot:run

# Found a bug?

Fork, improve and PR. ;-)

# Questions / Comments

Contact me at [http://dustin.schultz.io](http://dustin.schultz.io/)
