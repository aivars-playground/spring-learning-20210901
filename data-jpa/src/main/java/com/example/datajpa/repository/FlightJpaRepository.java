package com.example.datajpa.repository;

import com.example.datajpa.model.Flight;
import com.example.datajpa.projections.FlightModelProjection;
import com.example.datajpa.projections.ProjectionForNative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FlightJpaRepository extends JpaRepository<Flight, Long> {

    //Derived Query / Query DSL //compiles to JPQL -> SQL
    List<Flight> findBySource(String source);
    int countAllByDestination(String destination);
    Flight findTopByDestination(String destination);

    @Query(
            "select new com.example.datajpa.projections.FlightModelProjection(flight.source) from Flight flight"
    )
    List<FlightModelProjection> findProjections();

    @Query(nativeQuery = true,
            value = "select f.source as source from flights f"
    )
    List<ProjectionForNative> findProjectionsNative();

}