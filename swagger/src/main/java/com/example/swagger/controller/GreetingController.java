package com.example.swagger.controller;

import com.example.swagger.model.Greeting;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping(
            value = "/greeting",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Greeting greeting() {
        return new Greeting(1, "Hello");
    }

}
