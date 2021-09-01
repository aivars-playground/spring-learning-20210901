package org.example;

import org.example.repository.HibernatePersonRepositoryImpl;
import org.example.repository.PersonRepository;
import org.example.service.PersonService;
import org.example.service.PersonServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name = "personService")
    public PersonService getPersonService() {
        return new PersonServiceImpl(getPersonRepository());
    }

    @Bean(name = "personRepository")
    public PersonRepository getPersonRepository() {
        return new HibernatePersonRepositoryImpl();
    }
}
