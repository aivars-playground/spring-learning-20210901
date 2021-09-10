package com.example.datajdbc.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoController {

    @Resource
    JdbcTemplate jdbcTemplate;

    @GetMapping("/database")
    String startDemo() {
        var selectedDatabase = jdbcTemplate.queryForObject("SELECT DATABASE() FROM DUAL;", String.class);
        return "Selected Database:" + selectedDatabase;
    }

}
