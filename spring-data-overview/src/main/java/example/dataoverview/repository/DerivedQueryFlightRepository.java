package example.dataoverview.repository;

import example.dataoverview.entity.Flight;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DerivedQueryFlightRepository extends CrudRepository<Flight, Long> {

    //derived queries, transformed to data (i.e. sql) requests
    List<Flight> findByOrigin(String origin);
    List<Flight> findByOriginOrDestination(String origin, String destination);
    List<Flight> findByScheduledAtBetween(LocalDateTime from, LocalDateTime to);

}
