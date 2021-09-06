package com.example.aspect.controller;

import com.example.aspect.model.Person;
import com.example.aspect.service.basic.AopBasicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aopbasics")
public class AopBasicsController {

    private final AopBasicService aopBasicService;

    public AopBasicsController(AopBasicService aopBasicService) {
        this.aopBasicService = aopBasicService;
    }

    @GetMapping("/invokeLoggingAspect/{id}")
    public Person invokeLoggingAspect(@PathVariable Long id) {
        return aopBasicService.getPerson(id);
    }

}
