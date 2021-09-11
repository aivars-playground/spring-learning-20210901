package com.example.datajpa.repository;

import com.example.datajpa.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AirplaneRepositoryWithJPQL extends JpaRepository<Airplane, Long> {

    @Query(
            "select airplane from Airplane airplane " +
                    "where airplane.seats.size > 100"
    )
    List<Airplane> getOver100SeatAirplanes();

    @Query(
            "select airplane from Airplane airplane " +
                    "where airplane.seats.size > ?1"
    )
    List<Airplane> getAirplanesLargerThan(int seats);

    @Query(nativeQuery = true,
            value = "select * from airplanes where id in (" +
                    "    select airplane__id from seats" +
                    "    group by seats.airplane__id" +
                    "    having count(*) > ?1" +
                    ")"
    )
    List<Airplane> getNativeAirplanesLargerThan(int seats);

    @Query(
            "select airplane from Airplane airplane " +
                    "where airplane.seats.size <= :maxSeats"
    )
    List<Airplane> getAirplanesNamedQueryNoMoreThan(@Param("maxSeats") int maxSeats);

    @Query(nativeQuery = true,
            value = "select * from airplanes where id in (" +
                    "    select airplane__id from seats" +
                    "    group by seats.airplane__id" +
                    "    having count(*) <= ?1" +
                    ")"
    )
    List<Airplane> getNativeAirplanesNamedQueryNoMoreThan(@Param("maxSeats") int maxSeats);

    @Modifying(flushAutomatically = true)
    @Query(
            nativeQuery = true,
            value = "update airplanes a set tail_number = 'ZZZZZ'")
    void changeTailNumber();

    Airplane getAirplaneByTailNumber(String tailNumber);

    Airplane customNamedQueryFromEntitySelectByTailNr(@Param("tailNumber") String tailNumber);

}