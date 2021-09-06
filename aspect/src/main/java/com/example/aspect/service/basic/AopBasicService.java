package com.example.aspect.service.basic;

import com.example.aspect.model.Person;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AopBasicService {

    private Map<Long,Person> data = new HashMap<>();

    public Person getPerson(Long id) {
        if (data.containsKey(id)) {
            return data.get(id);
        } else {
            var person = new Person();
            person.setId(id);
            data.put(id, person);
            return data.get(id);
        }
    };

}
