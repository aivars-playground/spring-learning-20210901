package com.example.securityoauth.eventhandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationFailureHandler implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {

        Map<String, Object> data = new HashMap<>();
        data.put("timestamp", LocalDateTime.now().toString());
        data.put("user", event.getAuthentication().getPrincipal());
        data.put("exception",event.getException().getMessage());
        System.err.println("CustomAuthenticationFailureHandler:"+objectMapper.writeValueAsString(data));
    }
}
