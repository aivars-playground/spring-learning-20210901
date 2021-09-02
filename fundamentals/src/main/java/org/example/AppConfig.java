package org.example;

import org.example.util.CalendarFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

@Configuration
@ComponentScan({"org.example"})
public class AppConfig {
//
//    @Bean(name = "personService")
//    public PersonService getPersonService() {
//        return new PersonServiceImpl(getPersonRepository());
//    }
//
//    @Bean(name = "personRepository")
//    public PersonRepository getPersonRepository() {
//        return new HibernatePersonRepositoryImpl();
//    }

    @Bean(name = "calendarFactoryTomorrow")
    public CalendarFactory calendarFactoryTomorrow() {
        CalendarFactory factory = new CalendarFactory();
        factory.addDays(1);
        return factory;
    }

    @Bean(name = "calendarTomorrow")
    Calendar calendarTomorrow() {
        return calendarFactoryTomorrow().getObject();
    }

}
