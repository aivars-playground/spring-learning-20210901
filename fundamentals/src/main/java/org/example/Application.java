package org.example;

import org.example.service.PersonService;
import org.example.service.PersonServiceImpl;

public class Application {

    public static void main(String[] args) {
        PersonService service = new PersonServiceImpl();

        System.out.println(service.findAll().get(0).getFirstName());
    }

}
