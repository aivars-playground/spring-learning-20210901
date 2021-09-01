package org.example.repository;

import org.example.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("personRepository")
public class HibernatePersonRepositoryImpl implements PersonRepository {

    @Override
    public List<Person> findAll() {

        List<Person> persons = new ArrayList<>();

        Person person = new Person();
        person.setFirstName("FN");
        person.setLastName("LN");

        persons.add(person);

        return persons;
    }

}
