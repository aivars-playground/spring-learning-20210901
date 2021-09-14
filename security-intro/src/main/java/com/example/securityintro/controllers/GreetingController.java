package com.example.securityintro.controllers;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/greetingModifyCacheV1")
    public String greetingModifyCacheV1(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            final Model model,
            final HttpServletResponse response
    ) {
        response.addHeader("Hi","I am in a header");
        response.addHeader("Cache-Control", "max-age=1, must-revalidate, no-transform");
        //response.addCookie(new Cookie("who am I?","I-am-a-cookie"));//does not work,,,
        response.addCookie(new Cookie("who-am-I","I-am-a-cookie"));

        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/greetingModifyCacheV2")
    public ResponseEntity<String> greetingModifyCacheV2(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            final Model model
    ) {
        model.addAttribute("name", name);
        CacheControl cc = CacheControl.noCache();
        return ResponseEntity
                .ok()
                .cacheControl(cc)
                .body("greeting");
    }

}