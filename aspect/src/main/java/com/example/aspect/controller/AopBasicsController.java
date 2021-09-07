package com.example.aspect.controller;

import com.example.aspect.model.MixinForPerson;
import com.example.aspect.model.MixinForPersonImpl;
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

    private final Person person;

    public AopBasicsController(AopExampleService aopExampleService, Person person) {
        this.aopExampleService = aopExampleService;
        this.person = person;
    }

    @GetMapping("/invokeLoggingAspect/{id}")
    public Person invokeLoggingAspect(@PathVariable Long id) {
        return aopExampleService.getPerson(id);
    }

    @GetMapping("/invokeAlternativeLoggingAspect/{id}")
    public Person invokeAlternativeLoggingAspect(@PathVariable Long id) {
        return aopExampleService.getIndividual(id);
    }

    @GetMapping("/persoWithMixin")
    public MixinForPerson invokeAlternativeLoggingAspect() {
        ((MixinForPerson)person).setName("this person has a name");
        return (MixinForPerson)person;
    }


}
