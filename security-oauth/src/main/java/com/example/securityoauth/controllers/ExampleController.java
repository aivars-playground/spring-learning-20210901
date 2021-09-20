package com.example.securityoauth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExampleController {

    @GetMapping("/should_not_see_this")
    public String sanityTest() {
        return "dummy";
    }

    @GetMapping("/basic_auth")
    public String basicAuth() {
        return "dummy";
    }

    @GetMapping("/digest")
    public String digest() {
        return "dummy";
    }

    @GetMapping("/pin")
    public String pin() {
        return "dummy";
    }

    @GetMapping("/with_2fa")
    public String with2FA() {
        return "dummy";
    }

}
