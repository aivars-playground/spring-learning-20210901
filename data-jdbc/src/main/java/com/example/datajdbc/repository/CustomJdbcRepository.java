package com.example.datajdbc.repository;

import com.example.datajdbc.model.Flight;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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

    public void update(Flight flight) {
        jdbcTemplate.update("update flight set origin=?,destination=?, duration=? where id=?", flight.getOrigin(), flight.getDestination(), flight.getDuration(), flight.getId());
    }

    public Flight getOne(Integer id) {
        //using template method design pattern
        Flight flight = jdbcTemplate.queryForObject("select * from flight", (rs, rowNum) -> {
            Flight flight1 = new Flight();
            flight1.setId(rs.getInt("id"));
            flight1.setOrigin(rs.getString("origin"));
            flight1.setDestination(rs.getString("destination"));
            flight1.setDuration(rs.getInt("duration"));
            return flight1;
        });

        return flight;
    }



    public void deleteById(Integer id) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        namedParameterJdbcTemplate.update(
                "delete from flight where id = :id",
                paramMap
        );

    }


}
