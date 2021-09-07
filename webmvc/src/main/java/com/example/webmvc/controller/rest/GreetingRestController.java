package com.example.webmvc.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resttemplates")
public class GreetingRestController {

    @GetMapping("/hello")
    public String hello() {
        return "HELLO";
    }
}
