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
[WEB](http://localhost:8080/greeting)
```
 ${projectDir}/src/main/webapp/WEB-INF/jsp/greeting.jsp
 com.example.webmvc.controller.GreetingJspController
 spring.mvc.view.prefix=/WEB-INF/jsp/
 spring.mvc.view.suffix=.jsp
```