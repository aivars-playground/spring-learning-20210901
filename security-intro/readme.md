Spring Security training
=

when enabling Spring security (spring-boot-starter-security)  
default user 'user' is created, generated pw is in log...

```properties
management.endpoint.health.show-components=when_authorized
management.endpoint.health.show-details=when_authorized
```
```http request
###
http://localhost:8080/actuator/health

###
http://localhost:8080/actuator/health
Authorization: Basic user <token from console>
```

basic auth
-
no login, logout screen... triggers l/p popup
actuator/health is authorised as well...
```java
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("------------------------------------");
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic();
    }
}
```

in memory authentication
-
passwords are:
*stored as is: {noop}plainext
*stored with given encoding {encType}encodedPassword
```java
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("admin").password(encoder.encode("nimda")).roles("ADMIN")
                .and()
                .withUser("user").password("{noop}pass").roles();
    }
```