package com.example.securityoauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
public class SecurityOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityOauthApplication.class, args);
    }

}
