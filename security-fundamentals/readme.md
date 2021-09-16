Setup
=
This application is running on a tomcat...  


Debugging authorization
-
```java
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(true);
    }
```

Note on routing
-
localhost:8080, loacalhost:8080/, and loacalhost:8080/index.html points to the same location in our scenario
```xml
.antMatchers("/index*").permitAll()
```

Notes on MVC
-
No need to add an empty controller... if we need only view, it could be added as
```
public class AppWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/login");
    }
}
```

Login page and action:
-
Login page: can be customized (it needs to be a simple submit form)
Login action: spring provides an implementation, url can be customized:
```
.and()
.formLogin()
.loginPage("/login")
.loginProcessingUrl("/perform_login")   //no need to implement, has default implementation
```
DB based login
-
passwords are using bcrypt by default, generate example:
```
BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("----:"+encoder.encode("password"));

```
sql: sql_data.md


LDAP based login
-
an option - authenticate by one resource
add details, roles by another...


```java
auth.ldapAuthentication()
        ...
.and()
.userDetailsContextMapper(customUserContextMapper);
```

Mailhog
-
```
sendemail -f from@local -t to@domain.com -u subject -m "message" -s localhost:1025
```


Annotation Based Security
-
adding EnableGlobalMethodSecurity to WebSecurityConfigurerAdapter causes new tet uer to be created, see logs)
Using generated security password:



Getting current user
=
```java
    @GetMapping("/principal")
    public Principal loginDetails(
            Authentication auth,
            Principal principal) {

        System.out.println("---auth:"+auth);
        System.out.println("---auth.details:"+auth.getDetails());
        System.out.println("---principal:"+principal);

        var alternative = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("---alternative:"+alternative);


        return principal;
    }
```
