package com.example.securityoauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(96)
public class AppWebSecurityConfig096With2FA extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.mvcMatcher("/with_2fa")
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic();
    }
}
