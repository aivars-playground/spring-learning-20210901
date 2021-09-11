package com.example.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MyExtendedFlightRepositoryMixinStrategyTest {

    @Autowired
    MyExtendedFlightRepositoryMixinStrategy myExtendedFlightRepositoryMixinStrategy;

    @Test
    void testMixin() {
        assertThat(myExtendedFlightRepositoryMixinStrategy.getMixinMethod())
                .isEqualTo("Hi From Mixin");
    }
}