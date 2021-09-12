package com.example.datamongo.repository;

import com.example.datamongo.documents.Aircraft;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface AircraftMongoRepository extends MongoRepository<Aircraft, String> {
}
