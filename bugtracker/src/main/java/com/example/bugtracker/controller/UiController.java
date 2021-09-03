package com.example.bugtracker.controller;

import com.example.bugtracker.entity.Application;
import com.example.bugtracker.repository.ApplicationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("web")
public class UiController {

    private final ApplicationRepository applicationRepository;

    public UiController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @GetMapping("applications")
    public String listApplications(Model model) {
        model.addAttribute("applications", applicationRepository.findAll());
        return "applications";
    }
}
