package com.example.datajpa.repository;

import com.example.datajpa.model.Flight;
import com.example.datajpa.repository.mixin.MyMixin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyExtendedFlightRepositoryMixinStrategy
        extends JpaRepository<Flight, Long>, MyMixin
{

}
