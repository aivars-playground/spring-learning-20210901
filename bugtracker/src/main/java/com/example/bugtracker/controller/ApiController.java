package com.example.bugtracker.controller;

import com.example.bugtracker.entity.Application;
import com.example.bugtracker.repository.ApplicationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ApiController {

    private final ApplicationRepository applicationRepository;

    public ApiController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @GetMapping("application")
    public Iterable<Application> listApplications() {
        return applicationRepository.findAll();
    }
}
