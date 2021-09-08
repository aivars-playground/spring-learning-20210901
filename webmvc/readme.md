Demo WEB MVC app
=
enabling reload ... not exactly code hotswap...  
some changes are picked on Build,
in more advanced cases "Build/Rebuild" triggers reload
```xml
<artifactId>spring-boot-devtools</artifactId>
```
___
adding static index page  
[static index.html](src/main/resources/static/index.html)  
[WEB](http://localhost:8080/)

___
adding jsp page
[WEB](http://localhost:8080/jsptemplates/greeting)  
does not seem to be working in a standalone app
```
 ${projectDir}/src/main/webapp/WEB-INF/jsp/greeting.jsp
 com.example.webmvc.controller.jspstyle.GreetingJspController
 spring.mvc.view.prefix=/WEB-INF/jsp/
 spring.mvc.view.suffix=.jsp
```
___
@RestController vs @Controller  
@Controller will try to use template resolver to return web page  
@RestController would transform String to text/html, class to json

___
useful when some files must be protected... web inf is not browsable
```http request
GET http://localhost:8080/files/Screenshot.png
```

___
adding both thymeleaf and jsp resolvers  
```
com.example.webmvc.AppConfig
```
```http request
GET http://localhost:8080/thymeleaftemplates/example
```

___
rest controller can return multiple response Mime types:
```xml
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
        </dependency>
```
```java
    @GetMapping(
            value = "/example_user/1",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public User getUser() {
        return new User(){{setFirstName("FN");setLastName("LN");}};
    }
```
```http request
###
GET http://localhost:8080/resttexample/example_user/1
Accept: application/xml

###
GET http://localhost:8080/resttexample/example_user/1
Accept: application/json
```

default content type could be set in config bean
```java
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
```
thus enabling as json
```http request
###
GET http://localhost:8080/resttexample/example_user/1
```
