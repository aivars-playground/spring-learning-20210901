package com.example.datamongo.repository;

import com.example.datamongo.documents.Aircraft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AircraftMongoRepository extends MongoRepository<Aircraft, String> {

    Page<Aircraft> findAllByTailNrIsStartingWith(String tailNr, Pageable pageable);

    @Query("{$or : [{model: ?0}, {tail_nr : ?0}]}")
    List<Aircraft> itemsByQueryWithOr(String criteria);

    List<Aircraft> findAllBy(TextCriteria criteria, Sort sort);

    Optional<Aircraft> findTopByTailNr(String tailNr);

}
