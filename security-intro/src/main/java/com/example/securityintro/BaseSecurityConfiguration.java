package com.example.securityintro;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BaseSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("---BaseSecurityConfiguration.configure(HttpSecurity http)");
        http.authorizeRequests()
                    .mvcMatchers("/").permitAll()
                    .mvcMatchers("/login").permitAll()
                    .mvcMatchers("/logout").permitAll()
                    .mvcMatchers("/actuator/health").permitAll()
                .and().authorizeRequests()
                    .mvcMatchers("/actuator").hasRole("ACTUATOR_ADMIN")
                    .mvcMatchers("/actuator/**").hasRole("ACTUATOR_ADMIN")
                    .and().httpBasic()
                .and().authorizeRequests()
                    .mvcMatchers("/admin/**").hasRole("ADMIN")
                    .and().formLogin()
                .and().authorizeRequests()
                    .anyRequest().authenticated()
                    .and().formLogin().loginPage("/login")
                    .and().logout().logoutSuccessUrl("/");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        System.out.println("---BaseSecurityConfiguration.(WebSecurity web)");
        web.ignoring().mvcMatchers("/css/**", "/images/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("admin").password(encoder.encode("nimda")).roles("ADMIN")
                .and()
                .withUser("user").password("{noop}pass").roles()
                .and()
                .withUser("monitor").password("{noop}rotinom").roles("ACTUATOR_ADMIN");
    }
}
