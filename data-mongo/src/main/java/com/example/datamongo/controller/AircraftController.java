package com.example.datamongo.controller;

import com.example.datamongo.documents.Aircraft;
import com.example.datamongo.repository.AircraftMongoRepository;
import com.example.datamongo.repository.AircraftRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AircraftController {

    @Resource
    AircraftRepository aircraftRepository;

    @Resource
    AircraftMongoRepository aircraftMongoRepository;

    @GetMapping("/testme")
    void testMe() {
        Aircraft a = new Aircraft();
        a.setInvisible("not stored in db"); //null in returned json
        a.setModel("Hughes H-4 Hercules");
        a.setTailNr("AA0001");
        aircraftRepository.save(a);
    }

    @GetMapping("/readme")
    List<Aircraft> readme() {
        return aircraftMongoRepository.findAll();
    }


}
