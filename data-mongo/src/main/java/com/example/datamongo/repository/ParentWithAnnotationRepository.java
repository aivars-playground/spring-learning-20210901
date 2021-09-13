package com.example.datamongo.repository;

import com.example.datamongo.documents.Parent;
import com.example.datamongo.documents.ParentWithAnnotation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParentWithAnnotationRepository extends MongoRepository<ParentWithAnnotation, String> {
}
