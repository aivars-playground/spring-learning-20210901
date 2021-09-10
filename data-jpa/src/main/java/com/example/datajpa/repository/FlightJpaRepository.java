package com.example.datajpa.repository;

import com.example.datajpa.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightJpaRepository extends JpaRepository<Flight, Long> {

}