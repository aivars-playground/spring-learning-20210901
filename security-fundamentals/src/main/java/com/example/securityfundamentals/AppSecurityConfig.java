package com.example.securityfundamentals;

import com.example.securityfundamentals.customization.CustomUserContextMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/anonymous/*").anonymous()
                .antMatchers("/", "/index.html").permitAll()
                .antMatchers("/create_account").permitAll()
                .anyRequest().authenticated()

                .and()
                .rememberMe()
                .key("secret")
                .tokenRepository(persistentTokenRepository())

                .and()
                .formLogin()
                //.successHandler(getAuthSuccessHandler())
                //.failureHandler(getAuthFailureHandler()) //does not show error in log page...
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")    //no need to implement, has default implementation
                .failureUrl("/login?error=true")         //login?error is not picked up <c:if test="${not empty param.error}">, need /login?error=true
                .permitAll()

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/perform_logout", "GET"))
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        ;
    }

    @Bean
    PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
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

    @Resource
    private CustomUserContextMapper customUserContextMapper;

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
                    //.withUser("db_user")
                    //.password(passwordEncoder().encode("pass"))
                    //.roles("USER");
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
                    .passwordAttribute("userPassword")
                    .and()
                    .userDetailsContextMapper(customUserContextMapper);
        }
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/assets/css/**", "/assets/js/**", "/assets/images/**", "/webjars/**")
                //.and()
                //.debug(true)
        ;
    }

}
