package com.example.datajpa.repository;

import com.example.datajpa.model.Flight;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class MyExtendedFlightRepositoryProxyStrategy {

    @Resource
    FlightJpaRepository flightRepository;

    @Resource
    private JdbcTemplate jdbcTemplate;

    public Flight create(Flight flight) {
        return flightRepository.saveAndFlush(flight);
    }

    public Flight read(Long id) {
        return flightRepository.getById(id);
    }

    public Flight update(Flight flight) {
        return flightRepository.saveAndFlush(flight);
    }

    public void delete(Flight flight) {
        flightRepository.delete(flight);
    }

    public String databaseName() {
        return jdbcTemplate.queryForObject("SELECT current_database();", String.class);
    }

}
