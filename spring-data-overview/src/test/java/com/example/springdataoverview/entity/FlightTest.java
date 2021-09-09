package com.example.springdataoverview.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest //loads only subset of Spring functionality, quicker testing...
class FlightTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void flightEntityCanBeSaved() {

        TypedQuery<Flight> allFlightsQuery = entityManager.createQuery("select f from Flight f", Flight.class);

        Flight flight = new Flight() {{
            setOrigin("RIX");
            setDestination("NCL");
            setScheduledAt(LocalDateTime.of(2020,1,1,1,1));
        }};
        entityManager.persist(flight);

        List<Flight> queryResult = allFlightsQuery.getResultList();

        assertThat(queryResult)
                .hasSize(1)
                .first()
                .isEqualTo(flight);
    }
}