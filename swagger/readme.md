IMPORTANT
===
[springfox](http://springfox.github.io/springfox/) does not seem to be maintained anymore  
[springdoc](https://springdoc.org/) seems to be a successor

Swagger UI
===
[Swagger UI](http://localhost:8080/swagger-ui/)

add info to swagger page 
```
SpringFoxConfig.api 
  and
SpringFoxConfig.getApiInfo
```

add info to schema
[see schema](http://localhost:8080/swagger-ui/#/greeting-controller/greetingUsingGET)
```
public class Greeting {
    @ApiModelProperty("random text for swagger")
    private final String content;
}
```

add supported MIME types
```
http://localhost:8080/swagger-ui/#/greeting-controller/greetingUsingGET
dropdown in ui: Media type
```
```java
    @GetMapping(
            value = "/greeting",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Greeting greeting() {
        return new Greeting(1, "Hello");
    }
```



    

