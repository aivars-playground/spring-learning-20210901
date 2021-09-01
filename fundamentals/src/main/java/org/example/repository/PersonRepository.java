package org.example.repository;

import org.example.model.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> findAll();
}
