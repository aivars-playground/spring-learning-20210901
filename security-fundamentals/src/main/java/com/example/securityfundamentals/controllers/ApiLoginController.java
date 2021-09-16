package com.example.securityfundamentals.controllers;

import com.example.securityfundamentals.customization.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class ApiLoginController {

    @GetMapping("/principal")
    public Principal loginDetails(
            Authentication auth,
            Principal principal) {

        System.out.println("---auth:"+auth);
        System.out.println("---auth.details:"+auth.getDetails());
        System.out.println("---principal:"+principal);

        var alternative = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("---alternative:"+alternative);


        return principal;
    }
}
