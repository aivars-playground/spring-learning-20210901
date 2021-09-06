//AspectJ pointcut language
https://www.eclipse.org/aspectj/doc/next/progguide/language-joinPoints.html

point is something that happens in a language
* method call
* execution
* instantiation

Pointcuts pick out these join points... described by pointcut language
```pointcutexpression

execution(void Point.setX(int))
execution(* *.getPerson(..))
```
```
PersonGetterLoggingAspect
```
```http request
###
GET http://localhost:8080/api/aopbasics/invokeLoggingAspect/1
```

possible issue:   
https://docs.spring.io/spring-framework/docs/3.0.0.M3/reference/html/ch08s06.html   
if class does not implement interface, cglib might be used in aop... Would fail on final class, would not work on final method   
changing method signature would break aspect 


alternative - annotation and aspect
---
create interface - annotation and aspect for annotation
```
com.example.aspect.service.alternativeaspect.AlternativeLoggingAnnotation
com.example.aspect.service.alternativeaspect.AlternativeLoggingAspect
```

exceptions
---
```
com.example.aspect.service.exceptionaspect.CustomServiceExceptionAspect
```
```http request
GET http://localhost:8080/api/aopbasics/invokeAlternativeLoggingAspect/0
```