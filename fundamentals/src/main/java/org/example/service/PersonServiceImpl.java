package org.example.service;

import org.example.model.Person;
import org.example.repository.HibernatePersonRepositoryImpl;
import org.example.repository.PersonRepository;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    private PersonRepository repository = new HibernatePersonRepositoryImpl();

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }
}
