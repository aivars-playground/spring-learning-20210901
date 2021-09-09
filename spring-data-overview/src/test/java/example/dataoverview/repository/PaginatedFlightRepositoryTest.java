package example.dataoverview.repository;

import example.dataoverview.entity.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PaginatedFlightRepositoryTest {

    @Autowired
    PaginatedFlightRepository paginatedFlightRepository;

    @Test
    void sortingAndPaging() {

        Flight flight1 = new Flight();
        flight1.setOrigin("RIX");
        flight1.setDestination("NCL");
        flight1.setScheduledAt(LocalDateTime.of(2022, 1, 1, 1, 1));

        Flight flight2 = new Flight();
        flight2.setOrigin("RIX");
        flight2.setDestination("DFW");
        flight2.setScheduledAt(LocalDateTime.of(2023, 1, 1, 1, 1));

        Flight flight3 = new Flight();
        flight3.setOrigin("DFW");
        flight3.setDestination("RIX");
        flight3.setScheduledAt(LocalDateTime.of(2021, 1, 1, 1, 1));

        Flight flight4 = new Flight();
        flight4.setOrigin("RIX");
        flight4.setDestination("DFW");
        flight4.setScheduledAt(LocalDateTime.of(2020, 1, 1, 1, 1));

        paginatedFlightRepository.save(flight1);
        paginatedFlightRepository.save(flight2);
        paginatedFlightRepository.save(flight3);
        paginatedFlightRepository.save(flight4);


        Iterable<Flight> flightsSortedByDestinationAndScheule = paginatedFlightRepository.findAll(Sort.by("destination", "scheduledAt"));
        assertThat(flightsSortedByDestinationAndScheule)
                .hasSize(4)
                .usingRecursiveComparison()
                .isEqualTo(List.of(flight4,flight2,flight1,flight3));


        Page<Flight> secondPage = paginatedFlightRepository.findAll(PageRequest.of(1,3, Sort.by("id")));
        assertThat(secondPage.getTotalPages())
                .isEqualTo(2);
        assertThat(secondPage.get())
                .hasSize(1)
                .usingRecursiveComparison()
                .isEqualTo(List.of(flight4));
    }

    @Test
    void derivedRequestSortingAndPaging() {

        Flight flight1 = new Flight();
        flight1.setOrigin("RIX");
        flight1.setDestination("NCL");
        flight1.setScheduledAt(LocalDateTime.of(2022, 1, 1, 1, 1));

        Flight flight2 = new Flight();
        flight2.setOrigin("RIX");
        flight2.setDestination("DFW");
        flight2.setScheduledAt(LocalDateTime.of(2023, 1, 1, 1, 1));

        Flight flight3 = new Flight();
        flight3.setOrigin("DFW");
        flight3.setDestination("RIX");
        flight3.setScheduledAt(LocalDateTime.of(2021, 1, 1, 1, 1));

        Flight flight4 = new Flight();
        flight4.setOrigin("RIX");
        flight4.setDestination("DFW");
        flight4.setScheduledAt(LocalDateTime.of(2020, 1, 1, 1, 1));

        paginatedFlightRepository.save(flight1);
        paginatedFlightRepository.save(flight2);
        paginatedFlightRepository.save(flight3);
        paginatedFlightRepository.save(flight4);

        Page<Flight> rigaFlightsByTimeDescending = paginatedFlightRepository.findByOriginOrderByScheduledAtDesc("RIX", PageRequest.of(1,2));
        assertThat(rigaFlightsByTimeDescending.getTotalPages()).isEqualTo(2);
        assertThat(rigaFlightsByTimeDescending.getTotalElements()).isEqualTo(3);
        assertThat(rigaFlightsByTimeDescending.getNumberOfElements()).isEqualTo(1);
        assertThat(rigaFlightsByTimeDescending.getContent()).extracting(Flight::getScheduledAt).isEqualTo(List.of(LocalDateTime.of(2020, 1, 1, 1, 1)));

    }

}