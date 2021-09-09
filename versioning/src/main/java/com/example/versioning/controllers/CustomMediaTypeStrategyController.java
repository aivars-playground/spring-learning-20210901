package com.example.versioning.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/custom_media")
public class CustomMediaTypeStrategyController {

    @GetMapping(value = "/endpoint", produces = "application/vnd.example.api.v1+json")
    public String endpointV1() {
        return "CustomMediaTypeStrategyController response for v1";
    }

    @GetMapping(value = "/endpoint", produces = "application/vnd.example.api.v2+json")
    public String endpointV2() {
        return "CustomMediaTypeStrategyController response for v2";
    }
}
