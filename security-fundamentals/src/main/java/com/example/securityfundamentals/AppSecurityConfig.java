package com.example.securityfundamentals;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/anonymous/*").anonymous()
                .antMatchers("/assets/css/**", "/assets/js/**", "/assets/images/**", "/webjars/**").permitAll()
                .antMatchers("/", "/index.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(getAuthSuccessHandler())
                //.failureHandler(getAuthFailureHandler()) //does not show error in log page...
                .permitAll()
                .loginProcessingUrl("/perform_login")    //no need to implement, has default implementation
                .defaultSuccessUrl("/index.html", true);

        ;
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
    ExceptionMappingAuthenticationFailureHandler getAuthFailureHandler() {
        return new ExceptionMappingAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                System.out.println("--------------Failure:"+exception);
                super.onAuthenticationFailure(request, response, exception);
            }
        };
    }



    //default data source (configured in properties)
    @Resource
    private DataSource dataSource;

    @Value( "${my.custom.value.auth-type}" )
    private String authType;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        if (authType.equals("INMEMORY")) {
            System.out.println("--------------------inmemory:user/pass");
            auth.inMemoryAuthentication()
                    .withUser("user").password(passwordEncoder().encode("pass")).roles("USER");
        } else if(authType.equals("DB")) {
            System.out.println("--------------------DB::db_user/pass");
            auth.jdbcAuthentication().dataSource(dataSource);
//ADD user
//                    .withUser("db_user")
//                    .password(passwordEncoder().encode("pass"))
//                    .roles("USER");
        } else if(authType.equals("LDAP")) {
            System.out.println("--------------------LDAP:ldap_user/pass");
            auth.ldapAuthentication()
                    .userDnPatterns("uid={0},ou=people")
                    .groupSearchBase("ou=groups")
                    .contextSource()
                    .url("ldap://localhost:8389/dc=example,dc=com")
                    .and()
                    .passwordCompare()
                    .passwordEncoder(passwordEncoder())
                    .passwordAttribute("userPassword");
        }
    }
}
