package com.example.versioning.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@BasePathAwareController
@RequestMapping("/api/path_strategy")
public class UriPathStrategyController {

    @Value( "UriPathStrategyController: dummy response for selected profile:${spring.profiles.active:unknown}" )
    private String exampleResponse;

    @GetMapping("/endpoint")
    public String endpoint() {
        return exampleResponse;
    }
}
