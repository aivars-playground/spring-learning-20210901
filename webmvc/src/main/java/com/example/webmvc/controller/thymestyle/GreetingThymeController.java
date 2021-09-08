package com.example.webmvc.controller.thymestyle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/thymeleaftemplates")
public class GreetingThymeController {

    @GetMapping("/example")
    public String example(
            Map<String,Object> model,
            @RequestParam(value = "name", defaultValue = "???") String name
    ) {
        model.put("message", name);
        return "thymeleaf/example";
    }
}
