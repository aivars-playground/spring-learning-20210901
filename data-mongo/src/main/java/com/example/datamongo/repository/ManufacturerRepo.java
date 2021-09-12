package com.example.datamongo.repository;

import com.example.datamongo.documents.Manufacturer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface ManufacturerRepo extends MongoRepository<Manufacturer, String> {

}
