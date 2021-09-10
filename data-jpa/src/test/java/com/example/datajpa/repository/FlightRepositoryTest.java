package com.example.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FlightRepositoryTest {

    @Autowired
    FlightRepository flightRepository;

    @Test
    void emptyRepository() {
        assertThat(flightRepository.findAll())
                .hasSize(0);
    }
}