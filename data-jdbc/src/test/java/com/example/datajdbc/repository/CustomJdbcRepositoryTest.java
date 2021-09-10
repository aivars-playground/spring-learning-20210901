package com.example.datajdbc.repository;

import com.example.datajdbc.model.Flight;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Import(CustomJdbcRepository.class) //does not work without this line... why?
class CustomJdbcRepositoryTest {

    @Autowired
    private CustomJdbcRepository customJdbcRepository;

    @Test
    void testCRUD() {

        Flight flight = new Flight();
        flight.setOrigin("RIX");
        flight.setDestination("NCL");
        flight.setDuration(11);

        var id = customJdbcRepository.createWithSimpleInsertMapping(flight);

        assertThat(customJdbcRepository.getFlights())
                .usingRecursiveComparison(RecursiveComparisonConfiguration.builder().withIgnoredFields("id").build())
                .ignoringCollectionOrder()
                .isEqualTo(List.of(flight));

        Flight update = new Flight();
        update.setId(id);
        update.setOrigin("RIX");
        update.setDestination("DFW");
        update.setDuration(11);
        customJdbcRepository.update(update);

        assertThat(customJdbcRepository.getOne(id)).usingRecursiveComparison().isEqualTo(update);

        customJdbcRepository.deleteById(id);
        assertThat(customJdbcRepository.getFlights())
                .isEmpty();
    }
}