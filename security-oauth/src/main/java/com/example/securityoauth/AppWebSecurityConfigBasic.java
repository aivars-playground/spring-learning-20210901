package com.example.securityoauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(99)
public class AppWebSecurityConfigBasic extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.mvcMatcher("/basic_auth")
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic();
    }
}
