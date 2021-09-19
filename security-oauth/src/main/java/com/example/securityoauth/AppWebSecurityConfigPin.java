package com.example.securityoauth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Configuration
@Order(97)
public class AppWebSecurityConfigPin extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/pin/**").authorizeRequests()
                .antMatchers("/pin").authenticated()
                .and().formLogin().loginPage("/pin/login_pin").permitAll()
                .authenticationDetailsSource(new AdditionalAuthenticationDetailsSource());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserWithToken testUser = new UserWithToken("aa", "{noop}bb", true, true, true, true, Collections.EMPTY_LIST );
        testUser.setPin("123456");

        var userDetailsService = new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                if(username.equals(testUser.getUsername())) {
                    return testUser;
                } else {
                    throw new UsernameNotFoundException("not found:"+username);
                }
            }
        };

        var additionalAuthProvider = new DaoAuthenticationProvider() {
            @Override
            protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
                super.additionalAuthenticationChecks(userDetails, authentication);
                AdditionalAuthenticationDetails details = (AdditionalAuthenticationDetails)authentication.getDetails();
                UserWithToken user = (UserWithToken) userDetails;
                System.out.println("---compare:"+user +" with:" + details + " " + user.getPin() +"/"+details.getSecurityPin() );
                if (!ObjectUtils.nullSafeEquals(user.getPin(),details.getSecurityPin())) {
                    throw new SecurityException("pins mismatch");
                }
                user.setPin("[HIDDEN****]");
            }
        };
        additionalAuthProvider.setUserDetailsService(userDetailsService);

        auth.authenticationProvider(additionalAuthProvider);
    }
}

class UserWithToken extends User {

    @Getter
    @Setter
    private String pin;

    public UserWithToken(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}



class AdditionalAuthenticationDetails extends WebAuthenticationDetails {

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String securityPin;

    public AdditionalAuthenticationDetails(HttpServletRequest request) {
        super(request);
        System.out.println("---got:"+request.getParameter("pin"));
        Optional.ofNullable(request.getParameter("pin")).ifPresent(this::setSecurityPin);
    }
}

class AdditionalAuthenticationDetailsSource extends WebAuthenticationDetailsSource {
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new AdditionalAuthenticationDetails(context);
    }
}