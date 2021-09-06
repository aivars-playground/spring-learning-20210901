package com.example.aspect.service;

import com.example.aspect.model.Person;
import com.example.aspect.service.alternativeaspect.AlternativeLoggingAnnotation;
import org.springframework.stereotype.Service;

@Service
public class AopExampleService {

    //if signature changes... PersonGetterLoggingAspect will not work....
    public Person getPerson(Long id) {
        return create(id);
    }

    @AlternativeLoggingAnnotation
    public Person getIndividual(Long id) { return create(id); }

    private Person create(Long id) {
        if (id < 1) throw new CustomServiceException("invalid id" + id);
        return new Person() {{setId(id);}};
    }


}
