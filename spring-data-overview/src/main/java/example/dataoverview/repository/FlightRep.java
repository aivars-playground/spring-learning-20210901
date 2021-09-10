package example.dataoverview.repository;

import example.dataoverview.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRep extends Repository<Flight, Long> {
    <S extends Flight> S save(S entity); //use correct signature!!!!
    List<Flight> findAll();
    List<Flight> findByOrigin(String origin);
    Page<Flight> findByOriginOrderByScheduledAtDesc(String origin, Pageable pageable); //use correct signature!!!!

    void removeFlightByScheduledAt(LocalDateTime scheduledAt);
}
