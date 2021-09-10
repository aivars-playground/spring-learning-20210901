package com.example.datajdbc.repository;

import com.example.datajdbc.model.Flight;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("customJdbcRepository")
public class CustomJdbcRepository {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public void create(Flight flight) {
        jdbcTemplate.update("insert into flight (origin,destination,duration) values (?,?,?)", flight.getOrigin(), flight.getDestination(), flight.getDuration());
    }

    public Integer createWithSimpleInsertMapping(Flight flight) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        List<String> columns = List.of(
                "origin",
                "destination",
                "duration"
        );

        simpleJdbcInsert.setTableName("flight");
        simpleJdbcInsert.setColumnNames(columns);
        simpleJdbcInsert.setGeneratedKeyName("id");

        Map<String, Object> data = new HashMap<>();
        data.put("origin", flight.getOrigin());
        data.put("destination", flight.getDestination());
        data.put("duration", flight.getDuration());

        return simpleJdbcInsert.executeAndReturnKey(data).intValue();
    }

    public List<Flight> getFlights() {
        //using template method design pattern
        List<Flight> flights = jdbcTemplate.query("select * from flight", new RowMapper<Flight>() {
            @Override
            public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
                Flight flight = new Flight();
                flight.setId(rs.getInt("id"));
                flight.setOrigin(rs.getString("origin"));
                flight.setDestination(rs.getString("destination"));
                flight.setDuration(rs.getInt("duration"));
                return flight;
            }
        });

        return flights;
    }

    public List<Flight> getFlightsLambda() {
        //using template method design pattern
        List<Flight> flights = jdbcTemplate.query("select * from flight", (rs, rowNum) -> {
                    Flight flight = new Flight();
                    flight.setId(rs.getInt("id"));
                    flight.setOrigin(rs.getString("origin"));
                    flight.setDestination(rs.getString("destination"));
                    flight.setDuration(rs.getInt("duration"));
                    return flight;
                });

        return flights;
    }



}
