package com.example.datajpa.repository;

import com.example.datajpa.model.Airplane;
import com.example.datajpa.model.Seat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AirplaneRepositoryWithJPQLTest {

    @Autowired
    AirplaneRepositoryWithJPQL airplaneRepositoryWithJPQL;

    @BeforeEach
    void beforeEach() {
        airplaneRepositoryWithJPQL.deleteAll();
    }

    @Test
    void getLargerAirplanes() {
        Airplane seat80Plane = createAirplane("AA0001", (short) 20, List.of("A", "C", "H", "J"));
        Airplane seat120Plane = createAirplane("BB0001", (short) 20, List.of("A", "B", "C", "H", "K", "J"));
        Airplane seat400Plane = createAirplane("CC0001", (short) 40, List.of("A", "B", "C", "D", "E", "F", "G", "H", "K", "J"));

        airplaneRepositoryWithJPQL.saveAll(List.of(seat80Plane, seat120Plane, seat400Plane));

        assertThat(airplaneRepositoryWithJPQL.getOver100SeatAirplanes())
                .extracting(Airplane::getTailNumber)
                .isEqualTo(List.of("BB0001", "CC0001"));

        assertThat(airplaneRepositoryWithJPQL.getAirplanesLargerThan(200))
                .extracting(Airplane::getTailNumber)
                .isEqualTo(List.of("CC0001"));

        assertThat(airplaneRepositoryWithJPQL.getNativeAirplanesLargerThan(200))
                .extracting(Airplane::getTailNumber)
                .isEqualTo(List.of("CC0001"));

        assertThat(airplaneRepositoryWithJPQL.getAirplanesNamedQueryNoMoreThan(100))
                .extracting(Airplane::getTailNumber)
                .isEqualTo(List.of("AA0001"));

        assertThat(airplaneRepositoryWithJPQL.getNativeAirplanesNamedQueryNoMoreThan(100))
                .extracting(Airplane::getTailNumber)
                .isEqualTo(List.of("AA0001"));

        assertThat(airplaneRepositoryWithJPQL.findAll()).extracting(Airplane::getTailNumber)
                .isEqualTo(List.of("AA0001", "BB0001", "CC0001"));


        assertThat(airplaneRepositoryWithJPQL.getAirplaneByTailNumber("AA0001"))
                .isNotNull();

    }

    Airplane createAirplane(String tailNumber, short rows, List<String> seatConfig) {
        Airplane airplane = new Airplane();
        airplane.setTailNumber(tailNumber);
        List<Seat> seatList = new ArrayList<>();
        for (short row = 1; row <= rows; row++) {
            for (String letter : seatConfig) {
                Seat seat = new Seat();
                seat.setRow(row);
                seat.setLetter(letter);
                seat.setAirplane(airplane);
                seat.setOccupied(false);
                seatList.add(seat);
            }
        }
        airplane.setSeats(seatList);
        return airplane;
    }
}