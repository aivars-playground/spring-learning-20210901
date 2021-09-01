package org.example;

import org.example.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        PersonService service = applicationContext.getBean("personService", PersonService.class);

        System.out.println(service.findAll().get(0).getFirstName());
    }

}
