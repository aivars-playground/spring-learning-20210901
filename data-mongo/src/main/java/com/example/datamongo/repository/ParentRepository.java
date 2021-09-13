package com.example.datamongo.repository;

import com.example.datamongo.documents.Manufacturer;
import com.example.datamongo.documents.Parent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParentRepository extends MongoRepository<Parent, String> {
}
