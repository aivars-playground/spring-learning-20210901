package com.example.datajdbc.controller;

import com.example.datajdbc.model.Flight;
import com.example.datajdbc.repository.CustomJdbcRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoController {

    @Resource
    JdbcTemplate jdbcTemplate;

    @Resource
    CustomJdbcRepository customJdbcRepository;

    @GetMapping("/selected_database")
    String selectedDatabase() {
        var selectedDatabase = jdbcTemplate.queryForObject("SELECT DATABASE() FROM DUAL;", String.class);
        return selectedDatabase;
    }

    @GetMapping("/save_flight")
    void saveFlight() {
        Flight flight = new Flight();
        flight.setOrigin("RIX");
        flight.setDestination("NCL");
        flight.setDuration(11);

        System.out.println("=save_flight===creating flight from:"+flight.getOrigin()+" to:"+flight.getDestination());

        customJdbcRepository.create(flight);

        System.out.println("=save_flight===created id unknown");
    }

    @GetMapping("/save_flight_get_id")
    Integer saveFlightGetId() {
        Flight flight = new Flight();
        flight.setOrigin("RIX");
        flight.setDestination("NCL");
        flight.setDuration(11);

        System.out.println("=save_flight===creating flight from:"+flight.getOrigin()+" to:"+flight.getDestination());

        var id = customJdbcRepository.createWithSimpleInsertMapping(flight);

        System.out.println("=save_flight===created id:"+id);

        return id;
    }
}
