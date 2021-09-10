package com.example.datajdbc.service;

import com.example.datajdbc.controller.DemoController;
import com.example.datajdbc.model.Flight;
import com.example.datajdbc.repository.CustomJdbcRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TransactionEnabledService {

    @Resource
    CustomJdbcRepository customJdbcRepository;

    public void savingFailingNonTransactional() {
        Flight flight1 = new Flight();
        flight1.setOrigin("RIX");
        flight1.setDestination("NCL");
        flight1.setDuration(11);

        Flight flight2 = new Flight();
        flight2.setDestination("NCL");
        flight2.setDuration(11);

        customJdbcRepository.createWithSimpleInsertMapping(flight1);
        customJdbcRepository.createWithSimpleInsertMapping(flight2);
    }

    @Transactional
    public void savingFailingTransactional() {
        Flight flight1 = new Flight();
        flight1.setOrigin("RIX");
        flight1.setDestination("NCL");
        flight1.setDuration(11);

        Flight flight2 = new Flight();
        flight2.setDestination("NCL");
        flight2.setDuration(11);

        customJdbcRepository.createWithSimpleInsertMapping(flight1);
        customJdbcRepository.createWithSimpleInsertMapping(flight2);
    }

}