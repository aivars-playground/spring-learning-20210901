package com.example.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Table;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyExtendedFlightRepositoryProxyStrategyTest {
    @Autowired
    MyExtendedFlightRepositoryProxyStrategy myExtendedFlightRepositoryProxyStrategy;

    @Test
    void getFbNameFromProxy() {
        assertThat(myExtendedFlightRepositoryProxyStrategy.databaseName())
                .isEqualTo("postgres");
    }
}