package com.example.webmvc.controller.rest;

import com.example.webmvc.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/resttexample")
public class GreetingRestController {

    @GetMapping("/greeting")
    public String greeting(Map<String,Object> model) { //---ignores model
        model.put("message", "HELLO");
        return "greeting";
    }

    @GetMapping(
            value = "/example_user/1",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public User getUser() {
        return new User(){{setFirstName("FN");setLastName("LN");}};
    }

}
