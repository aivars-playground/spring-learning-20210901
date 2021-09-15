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

Actuator role:
```java
management.endpoint.health.show-components=when_authorized
management.endpoint.health.show-details=when_authorized
management.security.roles=ACTUATOR_ADMIN
```

Headers:
=
https://www.baeldung.com/spring-mvc-cache-headers  
Control globally 
```
public class BaseSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("---BaseSecurityConfiguration.configure(HttpSecurity http)");
        //@formatter:off
        http
                //.headers().cacheControl().disable() <!-- not good idea to mess with cache globally.... -->
```
On endpoint level
```java
    @GetMapping("/greetingModifyCacheV1")
    public String greetingModifyCacheV1(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            final Model model,
            final HttpServletResponse response
    ) {
        response.addHeader("Hi","I am in a header");
        response.addHeader("Cache-Control", "max-age=1, must-revalidate, no-transform");
        //response.addCookie(new Cookie("who am I?","I-am-a-cookie"));//does not work,,,
        response.addCookie(new Cookie("who-am-I","I-am-a-cookie"));

        model.addAttribute("name", name);
        return "greeting";
    }
```

note..
-
if we use ResponseEntity, views are not resolved... string is returned...
```
@GetMapping("/greetingModifyCacheV2")
public ResponseEntity<String> greetingModifyCacheV2(
@RequestParam(name="name", required=false, defaultValue="World") String name,
final Model model
) {
```

NOTE
=
Intellij HTTP  client starts session, and stores data under .idea/httpRequests  
there are cookies.. for current session

Creating a self-signed certificate
=
```shell
keytool -genkey -alias mycertificate -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 1
```
```properties
server.port=8443
server.ssl.key-store-password=password
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=mycertificate
```
connection can be redirected from 8080 to 8443 with a redirect connector...

Encrypt properties file
===
* Download jasypt
```shell
./jasypt/encrypt.sh input=password password=secret
----OUTPUT----------------------
Algorithm:PBEWithMD5AndDES
n5UxejgIJYANcGvv3OXynxf7lDVrfNRn
```
```xml
<dependency>
    <groupId>com.github.ulisesbocchio</groupId>
    <artifactId>jasypt-spring-boot-starter</artifactId>
    <version>3.0.4</version>
</dependency>
```
```properties
server.ssl.key-store-password=ENC(n5UxejgIJYANcGvv3OXynxf7lDVrfNRn)
```

