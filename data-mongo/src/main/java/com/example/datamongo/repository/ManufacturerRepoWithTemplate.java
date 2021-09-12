package com.example.datamongo.repository;

import com.example.datamongo.documents.Manufacturer;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ManufacturerRepoWithTemplate {

    @Resource
    MongoTemplate mongoTemplate;

    public Manufacturer save(Manufacturer manufacturer) {
        return mongoTemplate.save(manufacturer);
    }
}
