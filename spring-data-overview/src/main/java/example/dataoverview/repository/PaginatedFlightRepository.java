package example.dataoverview.repository;

import example.dataoverview.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PaginatedFlightRepository extends PagingAndSortingRepository<Flight, Long> {

    //supports derived queries... NOTE: use Pageable interface!!!
    Page<Flight> findByOriginOrderByScheduledAtDesc(String origin, Pageable pageable);
}
