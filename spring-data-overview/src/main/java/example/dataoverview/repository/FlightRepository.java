package example.dataoverview.repository;

import example.dataoverview.entity.Flight;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, Long> {
}
