package com.example.bugtracker;

import com.example.bugtracker.entity.Application;
import com.example.bugtracker.repository.ApplicationRepository;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BugtrackerApplication {

    public static final Logger LOGGER = LoggerFactory.getLogger(BugtrackerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BugtrackerApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ApplicationRepository applicationRepository) {
        return (args) -> {
            LOGGER.info("-------ADDING___APP----");
            var res = applicationRepository.save(new Application("app", "my app", "me"));
        };
    }
}
