package com.example.datajpa.repository;

import com.example.datajpa.model.Flight;
import com.example.datajpa.projections.FlightModelProjection;
import com.example.datajpa.projections.ProjectionForNative;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FlightJpaRepositoryTest {

    @Autowired
    FlightJpaRepository flightJpaRepository;

    @Test
    void emptyRepository() {
        assertThat(flightJpaRepository.findAll())
                .hasSize(0);
    }

    @Test
    void returnProjectionRepository() {
        assertThat(flightJpaRepository.findAll())
                .hasSize(0);

        Flight flight = new Flight();
        flight.setSource("aaa");
        flight.setDestination("bbb");

        flightJpaRepository.saveAndFlush(flight);

        assertThat(flightJpaRepository.findAll())
                .hasSize(1);

        assertThat(flightJpaRepository.findProjections())
                .isEqualTo(List.of(new FlightModelProjection("aaa")));

        assertThat(flightJpaRepository.findProjectionsNative())
                .extracting(ProjectionForNative::getSource).isEqualTo(List.of("aaa"));
    }
}