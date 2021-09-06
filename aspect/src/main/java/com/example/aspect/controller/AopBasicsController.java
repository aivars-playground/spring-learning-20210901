package com.example.aspect.controller;

import com.example.aspect.model.Person;
import com.example.aspect.service.AopExampleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aopbasics")
public class AopBasicsController {

    private final AopExampleService aopExampleService;

    public AopBasicsController(AopExampleService aopExampleService) {
        this.aopExampleService = aopExampleService;
    }

    @GetMapping("/invokeLoggingAspect/{id}")
    public Person invokeLoggingAspect(@PathVariable Long id) {
        return aopExampleService.getPerson(id);
    }

    @GetMapping("/invokeAlternativeLoggingAspect/{id}")
    public Person invokeAlternativeLoggingAspect(@PathVariable Long id) {
        return aopExampleService.getIndividual(id);
    }

}
