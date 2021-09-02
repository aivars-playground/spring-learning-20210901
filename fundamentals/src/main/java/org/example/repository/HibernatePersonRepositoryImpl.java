package org.example.repository;

import org.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository("personRepository")
public class HibernatePersonRepositoryImpl implements PersonRepository {

    @Autowired
    @Qualifier("calendarTomorrow")
    private Calendar calendarTomorrow;

    @Override
    public List<Person> findAll() {

        List<Person> persons = new ArrayList<>();

        Person person = new Person();
        person.setFirstName("FN");
        person.setLastName("LN");

        persons.add(person);

        System.out.println("tomorrow is:" +calendarTomorrow.getTime());

        return persons;
    }

}
