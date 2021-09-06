package com.example.aspect.service;

import com.example.aspect.model.Person;
import com.example.aspect.service.alternativeaspect.AlternativeLoggingAnnotation;
import org.springframework.stereotype.Service;

@Service
public class AopExampleService {

    //if signature changes... PersonGetterLoggingAspect will not work....
    public Person getPerson(Long id) {
        return new Person() {{setId(id);}};
    }

    @AlternativeLoggingAnnotation
    public Person getIndividual(Long id) {
        return new Person() {{setId(id);}};
    }



}
