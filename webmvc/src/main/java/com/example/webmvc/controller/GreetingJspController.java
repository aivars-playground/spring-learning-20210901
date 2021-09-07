package com.example.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class GreetingJspController {

    @GetMapping("/greeting")
    public String greeting(Map<String,Object> model) {
        model.put("message", "HELLO");
        return "greeting";
    }
}
