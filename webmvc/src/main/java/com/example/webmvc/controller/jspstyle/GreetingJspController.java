package com.example.webmvc.controller.jspstyle;

import com.example.webmvc.model.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/jsptemplates")
public class GreetingJspController {

    //looks like JSP templates are not working correctly in a standalone Spring Boot

    @GetMapping("/greeting")
    public String greeting(
            Map<String,Object> model,
            @RequestParam(value = "name", defaultValue = "???") String name
    ) {
        model.put("message", name);
        return "jsp/greeting";
    }

    @GetMapping("/registration")
    public String getRegistration(@ModelAttribute("registration") Registration registration) {
        return "jsp/registration";
    }

    @PostMapping("/registration")
    public String addRegistration(
            @Valid @ModelAttribute("registration") Registration registration,
            BindingResult bindingResult
    ) {
        System.out.println("------------process registration, errors:"+bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            return "jsp/registration";
        } else {
            return "redirect:registration";
        }
    }
}
