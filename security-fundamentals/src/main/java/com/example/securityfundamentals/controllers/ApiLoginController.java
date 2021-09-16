package com.example.securityfundamentals.controllers;

import com.example.securityfundamentals.customization.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class ApiLoginController {

    @GetMapping("/principal")
    public Principal loginDetails(Principal principal) {
        return principal;
    }
}
