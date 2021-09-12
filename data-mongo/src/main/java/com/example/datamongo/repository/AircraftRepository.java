package com.example.datamongo.repository;

import com.example.datamongo.documents.Aircraft;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AircraftRepository extends CrudRepository<Aircraft, String> {
}
