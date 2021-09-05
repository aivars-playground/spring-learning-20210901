package com.example.bugtracker.controller;

import com.example.bugtracker.entity.Application;
import com.example.bugtracker.repository.ApplicationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1")
public class ApiController {

    private final ApplicationRepository applicationRepository;

    public ApiController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @GetMapping("applications")
    public Iterable<Application> listApplications() {
        return applicationRepository.findAll();
    }


    @GetMapping("applications/{id}")
    public Application read(@PathVariable long id) {
        return applicationRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "app not found"));
    }
}
