package com.example.versioning.controller.uristrategy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/url_strategy")
public class UriStrategyControllerV1 {

    @GetMapping("endpoint")
    public String endpoint() {
        return "uri strategy endpoint v1";
    }

}
