package example.dataoverview.repository;

import example.dataoverview.entity.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FlightRepTest {

    @Autowired
    private FlightRep flightRepository;

    @Test
    void flightEntityCanBeSaved() {

        Flight flight1 = new Flight();
        flight1.setOrigin("RIX");
        flight1.setDestination("NCL");
        flight1.setScheduledAt(LocalDateTime.of(2020, 1, 1, 1, 1));

        Flight flight2 = new Flight();
        flight2.setOrigin("DFW");
        flight2.setDestination("NCL");
        flight2.setScheduledAt(LocalDateTime.of(2021, 1, 1, 1, 1));

        Flight flight3 = new Flight();
        flight3.setOrigin("RIX");
        flight3.setDestination("NCL");
        flight3.setScheduledAt(LocalDateTime.of(2021, 1, 1, 1, 1));

        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);


        assertThat(flightRepository.findAll())
                .hasSize(3)
                .isEqualTo(List.of(flight1,flight2, flight3));

        assertThat(flightRepository.findByOrigin("DFW"))
                .hasSize(1)
                .isEqualTo(List.of(flight2));

        Page<Flight> rigaFlightsByDateDesc = flightRepository.findByOriginOrderByScheduledAtDesc("RIX", PageRequest.of(0,5));
        assertThat(rigaFlightsByDateDesc.getTotalElements()).isEqualTo(2);
        assertThat(rigaFlightsByDateDesc.getNumberOfElements()).isEqualTo(2);
        assertThat(rigaFlightsByDateDesc.getContent()).first().isEqualTo(flight3);

        flightRepository.removeFlightByScheduledAt(LocalDateTime.of(2021, 1, 1, 1, 1));
        assertThat(flightRepository.findAll())
                .hasSize(1)
                .isEqualTo(List.of(flight1));
    }

}