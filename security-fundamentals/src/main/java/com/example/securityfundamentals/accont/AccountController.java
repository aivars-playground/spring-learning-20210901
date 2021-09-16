package com.example.securityfundamentals.accont;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class AccountController {

    @Resource
    AccountService accountService;

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("create_account")
    public String startCreate(
            @ModelAttribute("account") Account account
    ) {
        System.out.println("---startCreate");
        return "create_account";
    }

    @PostMapping("create_account")
    public String processCreate(
            @ModelAttribute("account") Account account,
            BindingResult bindingResult
    ) {
        System.out.println("---processCreate");

        //encrypt password
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        //create account
        account = accountService.create(account);

        //publish event to send an email
        applicationEventPublisher.publishEvent(new AccountOnCreateEvent(
                account,
                "http:/localhost:8080"
        ));

        return "redirect:create_account";
    }

}
