package com.example.datamongo.controller;

import com.example.datamongo.documents.*;
import com.example.datamongo.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
public class AircraftController {

    @Resource
    AircraftRepository aircraftRepository;

    @Resource
    AircraftMongoRepository aircraftMongoRepository;

    @Resource
    ManufacturerRepo manufacturerRepo;

    @Resource
    ManufacturerRepoWithTemplate manufacturerRepoWithTemplate;

    @Resource
    ParentWithAnnotationRepository parentWithAnnotationRepository;

    @Resource
    ParentRepository parentRepository;

    @GetMapping("/addTestData")
    void addTestData() {
        Aircraft a = new Aircraft();
        a.setInvisible("not stored in db"); //null in returned json
        a.setModel("Hughes H-4 Hercules");
        a.setTailNr("AA0001");

        Manufacturer hughes = new Manufacturer();
        hughes.setName("Hughes Aircraft Company");
        hughes.setCountry("US");

        a.setManufacturer(hughes);

        manufacturerRepo.save(hughes);
        aircraftRepository.save(a);
    }

    @GetMapping("/addTestDataInBatch")
    void addTestDataInBatch() {
        Manufacturer m1 = new Manufacturer();
        m1.setName("MANUFACTURER1");
        m1.setCountry("US");
        Manufacturer m2 = new Manufacturer();
        m2.setName("MANUFACTURER2");
        m2.setCountry("US");
        manufacturerRepo.saveAll(List.of(m1,m2));
    }


    @GetMapping("/readme")
    List<Aircraft> readme() {
        return aircraftMongoRepository.findAll();
    }

    @GetMapping("/testTemplate")
    Manufacturer testTemplate() {
        Manufacturer raf = new Manufacturer();
        raf.setName("raf");
        raf.setCountry("LV");
        return  manufacturerRepoWithTemplate.save(raf);
    }

    @GetMapping("/testTemplateWithQuery")
    Page<Aircraft> testTemplateWithQuery() {
        var firstPage = PageRequest.of(0,10, Sort.by("manufacturer"));
        var res = aircraftMongoRepository.findAllByTailNrIsStartingWith("AA",firstPage);
        return res;
    }

    @GetMapping("/testTemplateWithQueryInAnnotation")
    List<Aircraft> testTemplateWithQueryInAnnotation() {
        return aircraftMongoRepository.itemsByQueryWithOr("AA0001");
    }

    @GetMapping("/testTemplateWithFullText")
    List<Aircraft> testTemplateWithFullText() {
        Sort sort = Sort.by("tail_nr");
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny("AA0001", "Spruce");
        return aircraftMongoRepository.findAllBy(criteria, sort);
    }

    @GetMapping("/update")
    void update() {
        Optional<Aircraft> maybeAircraft = aircraftMongoRepository.findTopByTailNr("AA0001");
        maybeAircraft.ifPresent(
                aircraft -> {
                    aircraft.setModel("Spruce Goose");
                    aircraftRepository.save(aircraft);
                }
        );
    }

    @GetMapping("/updateWithTemplate")
    String updateWithTemplate() {
        var updated = manufacturerRepoWithTemplate.fixDataWithQuery();
        return "Updated:"+updated;
    }

    @GetMapping("/removeManufacturersWithTemplate")
    List<Manufacturer> removeManufacturersWithTemplate() {
        return manufacturerRepoWithTemplate.removeAll();
    }

    @GetMapping("/cascade")
    Parent cascade() {
        Parent parent = new Parent();
        parent.setName("p1");

        Child child = new Child();
        child.setName("c1");

        parent.setChild(child);

        return parentRepository.save(parent);
    }

    @GetMapping("/cascadeWithAnnotation")
    ParentWithAnnotation cascadeWithAnnotation() {
        ParentWithAnnotation pwa1 = new ParentWithAnnotation();
        pwa1.setName("pwa1");

        Child child = new Child();
        child.setName("c_for_pwa1");

        pwa1.setChild(child);

        return parentWithAnnotationRepository.save(pwa1);
    }

}
