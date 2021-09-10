package com.example.datajpa.repository;

import com.example.datajpa.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FlightJpaRepository extends JpaRepository<Flight, Long> {

    //Derived Query / Query DSL //compiles to JPQL -> SQL
    List<Flight> findBySource(String source);
    int countAllByDestination(String destination);
    Flight findTopByDestination(String destination);



}