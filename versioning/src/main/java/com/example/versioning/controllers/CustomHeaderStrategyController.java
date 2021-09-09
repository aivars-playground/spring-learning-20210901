package com.example.versioning.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/custom_header")
public class CustomHeaderStrategyController {

    @GetMapping(value = "/endpoint", headers =  "Accept-version=v1")
    public String endpointV1() {
        return "CustomHeaderStrategyController response for Accept-version=v1";
    }

    @GetMapping(value = "/endpoint", headers =  "Accept-version=v2")
    public String endpointV2() {
        return "CustomHeaderStrategyController response for Accept-version=v2";
    }
}
