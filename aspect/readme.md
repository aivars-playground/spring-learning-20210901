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
com.example.aspect.service.basic.PersonGetterLoggingAspect
```
```http request
###
GET http://localhost:8080/api/aopbasics/invokeLoggingAspect/1
```