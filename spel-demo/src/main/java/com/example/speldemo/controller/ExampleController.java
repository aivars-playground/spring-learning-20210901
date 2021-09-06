package com.example.speldemo.controller;

import com.example.speldemo.model.Order;
import com.example.speldemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExampleController {

    @Autowired
    Order order;

    @Autowired
    User user;

    @GetMapping("/customer")
    public User getUser() {
        return user;
    }

    @GetMapping("/order")
    public Order getOrder() {
        return order;
    }
}
