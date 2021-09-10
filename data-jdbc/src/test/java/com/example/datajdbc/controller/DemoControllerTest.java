package com.example.datajdbc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class DemoControllerTest {

    @Autowired
    DemoController demoController;

    @Test
    void test() {
        assertThat(demoController.startDemo())
                .isEqualTo("zzz");
    }
}