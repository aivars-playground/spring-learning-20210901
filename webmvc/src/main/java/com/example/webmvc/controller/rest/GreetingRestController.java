package com.example.webmvc.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/resttemplates")
public class GreetingRestController {

    @GetMapping("/greeting")
    public String greeting(Map<String,Object> model) {
        model.put("message", "HELLO");
        return "greeting";
    }
}
