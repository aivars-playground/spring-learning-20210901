package com.example.datajpa.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "airplanes")
@NamedQuery(
        name="Airplane.customNamedQueryFromEntitySelectByTailNr",
        query = "select airplane from Airplane airplane where airplane.tailNumber = :tailNumber"
)
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String tailNumber;

    @OneToMany(mappedBy = "airplane", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }
}
