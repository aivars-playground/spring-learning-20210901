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