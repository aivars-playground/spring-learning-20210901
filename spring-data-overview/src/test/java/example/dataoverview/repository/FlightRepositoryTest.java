package example.dataoverview.repository;

import example.dataoverview.entity.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest //loads only subset of Spring functionality, quicker testing...
class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    void flightEntityCanBeSaved() {

        Flight flight = new Flight();
        flight.setOrigin("RIX");
        flight.setDestination("NCL");
        flight.setScheduledAt(LocalDateTime.of(2020, 1, 1, 1, 1));

        assertThat(flightRepository.findAll())
                .isEmpty();

        flightRepository.save(flight);

        assertThat(flightRepository.findAll())
                .hasSize(1)
                .first()
                .isEqualTo(flight);

        flightRepository.deleteById(flight.getId());

        assertThat(flightRepository.findAll())
                .isEmpty();
    }

}