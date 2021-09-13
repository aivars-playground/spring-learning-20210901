package com.example.securityintro;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class BaseSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("---BaseSecurityConfiguration.configure(HttpSecurity http)");
        http.authorizeRequests()
                .mvcMatchers("/").permitAll()
                .mvcMatchers("/actuator/health").permitAll()
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/logout").permitAll()
                .mvcMatchers("/admin/*").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and().formLogin().loginPage("/login")
            .and().logout().logoutSuccessUrl("/");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        System.out.println("---BaseSecurityConfiguration.(WebSecurity web)");
        web.ignoring().mvcMatchers("/css/**", "/images/**");
    }
}
