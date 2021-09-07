package com.example.webmvc.controller.jsptemplate;

import com.example.webmvc.model.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/jsptemplates")
public class GreetingJspController {

    //looks like JSP templates are not working correctly in a standalone Spring Boot

    @GetMapping("/greeting")
    public String greeting(Map<String,Object> model) {
        model.put("message", "HELLO");
        return "greeting";
    }

    @GetMapping("/registration")
    public String getRegistration(@ModelAttribute("registration") Registration registration) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addRegistration(@ModelAttribute("registration") Registration registration) {
        return "registration";
    }
}
