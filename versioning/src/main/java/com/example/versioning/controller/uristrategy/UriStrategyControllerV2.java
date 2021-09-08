package com.example.versioning.controller.uristrategy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v2/url_strategy")
public class UriStrategyControllerV2 {

    @GetMapping("endpoint")
    public String endpoint() {
        return "uri strategy endpoint v2";
    }

}
