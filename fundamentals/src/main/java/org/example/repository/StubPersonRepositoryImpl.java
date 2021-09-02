package org.example.repository;

import org.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository("personRepository")
@Profile("stub")
public class StubPersonRepositoryImpl implements PersonRepository {

    @Autowired
    @Qualifier("calendarTomorrow")
    private Calendar calendarTomorrow;

    @Value("#{T(java.lang.Math).random()}")
    private double rand;

    @Override
    public List<Person> findAll() {

        List<Person> persons = new ArrayList<>();

        Person person = new Person();
        person.setFirstName("FN:"+rand);
        person.setLastName("LN");

        persons.add(person);

        System.out.println("tomorrow is:" +calendarTomorrow.getTime());

        return persons;
    }

}
