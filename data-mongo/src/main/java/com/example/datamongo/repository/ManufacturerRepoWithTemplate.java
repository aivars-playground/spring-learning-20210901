package com.example.datamongo.repository;

import com.example.datamongo.documents.Manufacturer;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ManufacturerRepoWithTemplate {

    @Resource
    MongoTemplate mongoTemplate;

    public Manufacturer save(Manufacturer manufacturer) {
        return mongoTemplate.save(manufacturer);
    }

    public long fixDataWithQuery() {
        Query rafManufacturer = Query.query(Criteria.where("name").is("raf"));
        Update capitalizeName = Update.update("name", "RAF");

        return mongoTemplate.updateMulti(rafManufacturer, capitalizeName, Manufacturer.class).getModifiedCount();
    }

    public List<Manufacturer> removeAll() {
        Query all = new Query();
        return mongoTemplate.findAllAndRemove(all, Manufacturer.class);
    }
}
