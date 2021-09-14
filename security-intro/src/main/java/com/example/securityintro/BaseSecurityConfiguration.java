package com.example.securityintro;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class BaseSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("---BaseSecurityConfiguration.configure(HttpSecurity http)");
        //@formatter:off
        http
                //.headers().cacheControl().disable() <!-- not good idea to mess with cache globally.... -->
                .authorizeRequests()
                    .mvcMatchers("/").permitAll()
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
                    .and().formLogin()
                        .loginPage("/login")
                        .successHandler(getAuthSuccessHandler())
                        .failureHandler(getAuthFailureHandler())
                        .permitAll()
                    .and().logout()
                        .logoutSuccessUrl("/")
                        .permitAll();
        //@formatter:on
    }

    @Bean
    AuthenticationSuccessHandler getAuthSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
                System.out.println("--------------Success:"+authentication);
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }

    @Bean
    AuthenticationFailureHandler getAuthFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                System.out.println("--------------Failure:"+exception);
                super.onAuthenticationFailure(request, response, exception);
            }
        };
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
