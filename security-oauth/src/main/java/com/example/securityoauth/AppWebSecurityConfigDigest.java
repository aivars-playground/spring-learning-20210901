package com.example.securityoauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;
import org.springframework.util.DigestUtils;

import javax.servlet.Filter;
import java.util.List;

@Configuration
@Order(98)
public class AppWebSecurityConfigDigest extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/digest*")
                .addFilter(getDigestAuthFilter()).exceptionHandling()
                .authenticationEntryPoint(getDigestEntryPoint())
                .and().authorizeRequests().antMatchers("/digest*").hasRole("ADMIN")
        ;
    }

    DigestAuthenticationEntryPoint getDigestEntryPoint() {
        DigestAuthenticationEntryPoint digestAuthenticationEntryPoint = new DigestAuthenticationEntryPoint();
        digestAuthenticationEntryPoint.setRealmName("my-secure-realm");
        digestAuthenticationEntryPoint.setKey("my-digest-key");
        return digestAuthenticationEntryPoint;
    }

    private DigestAuthenticationFilter getDigestAuthFilter() {
        DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
        digestAuthenticationFilter.setUserDetailsService(super.userDetailsService());
        digestAuthenticationFilter.setAuthenticationEntryPoint(getDigestEntryPoint());

        //if password is already in ha1
        digestAuthenticationFilter.setPasswordAlreadyEncoded(true);
        digestAuthenticationFilter.setCreateAuthenticatedToken(true);


        return digestAuthenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var HA1 = DigestUtils.md5DigestAsHex("admin:my-secure-realm:nimda".getBytes()); //MD5(<user>:<realm>:<password>)
        auth.inMemoryAuthentication()
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("admin").password(HA1).roles("ADMIN");
    }
}
