package example.dataoverview.repository;

import example.dataoverview.entity.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest //loads only subset of Spring functionality, quicker testing...
class DerivedQueryFlightRepositoryTest {

    @Autowired
    private DerivedQueryFlightRepository flightRepository;

    @Test
    void derivedQueries() {

        Flight flight1 = new Flight();
        flight1.setOrigin("RIX");
        flight1.setDestination("NCL");
        flight1.setScheduledAt(LocalDateTime.of(2022, 1, 1, 1, 1));

        Flight flight2 = new Flight();
        flight2.setOrigin("RIX");
        flight2.setDestination("DFW");
        flight2.setScheduledAt(LocalDateTime.of(2020, 1, 1, 1, 1));

        Flight flight3 = new Flight();
        flight3.setOrigin("DFW");
        flight3.setDestination("NCL");
        flight3.setScheduledAt(LocalDateTime.of(2023, 1, 1, 1, 1));

        Flight flight4 = new Flight();
        flight4.setOrigin("DFW");
        flight4.setDestination("RIX");
        flight4.setScheduledAt(LocalDateTime.of(2021, 1, 1, 1, 1));

        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);
        flightRepository.save(flight4);

        List flightsFromRiga = flightRepository.findByOrigin("RIX");
        assertThat(flightsFromRiga)
                .hasSize(2)
                .usingRecursiveComparison()
                .isEqualTo(List.of(flight1,flight2));

        List flightsFromOrToRiga = flightRepository.findByOriginOrDestination("RIX", "RIX");
        assertThat(flightsFromOrToRiga)
                .hasSize(3)
                .usingRecursiveComparison()
                .isEqualTo(List.of(flight1,flight2, flight4));

        List flightsIn2020 = flightRepository.findByScheduledAtBetween(
                LocalDateTime.of(2020, 1, 1, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59));

        assertThat(flightsIn2020)
                .hasSize(1)
                .usingRecursiveComparison()
                .isEqualTo(List.of(flight2));
    }

}