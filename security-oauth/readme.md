Configuration
=

Spring autoconfiguration
-
```
spring-boot-autoconfigure-2.5.4.jar
/META-inf/spring.factories
```

default filter configuration:
```
org.springframework.web.filter.DelegatingFilterProxy.doFilter
filters = {ArrayList@7377}  size = 15
        0 = {WebAsyncManagerIntegrationFilter@7380}
        1 = {SecurityContextPersistenceFilter@7381}
        2 = {HeaderWriterFilter@7382}
        3 = {CsrfFilter@7383}
        4 = {LogoutFilter@7384}
        5 = {UsernamePasswordAuthenticationFilter@7385}
        6 = {DefaultLoginPageGeneratingFilter@7386}
        7 = {DefaultLogoutPageGeneratingFilter@7387}
        8 = {BasicAuthenticationFilter@7388}
        9 = {RequestCacheAwareFilter@7389}
        10 = {SecurityContextHolderAwareRequestFilter@7390}
        11 = {AnonymousAuthenticationFilter@7391}
        12 = {SessionManagementFilter@7392}
        13 = {ExceptionTranslationFilter@7393}
        14 = {FilterSecurityInterceptor@7394} 
```

basic
```java
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated().and().httpBasic().and().logout();
    }
```
```
filters = {ArrayList@7373}  size = 12
 0 = {WebAsyncManagerIntegrationFilter@7376} 
 1 = {SecurityContextPersistenceFilter@7377} 
 2 = {HeaderWriterFilter@7378} 
 3 = {CsrfFilter@7379} 
 4 = {LogoutFilter@7380} 
 5 = {BasicAuthenticationFilter@7381} 
 6 = {RequestCacheAwareFilter@7382} 
 7 = {SecurityContextHolderAwareRequestFilter@7383} 
 8 = {AnonymousAuthenticationFilter@7384} 
 9 = {SessionManagementFilter@7385} 
 10 = {ExceptionTranslationFilter@7386} 
 11 = {FilterSecurityInterceptor@7387} 
```

digest
-
Spring deprecates digest... passwords gust be stored in a plain text... or in HA1 encoding...
```
nonce = <expiryTime> + ":" + md5hex(<expiryTime> + <key>
ha1 = MD5(<user>:<realm>:<password>)
AppWebSecurityConfigDigest
```


