package com.example.datajdbc.repository;

import com.example.datajdbc.model.Flight;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
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

}
