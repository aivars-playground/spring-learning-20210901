package org.example.repository;

import org.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository("personRepository")
@Profile("live")
public class LivePersonRepositoryImpl implements PersonRepository {

    @Override
    public List<Person> findAll() {
        throw new RuntimeException("not implemented");
    }

}
