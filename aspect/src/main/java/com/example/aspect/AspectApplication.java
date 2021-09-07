package com.example.aspect;

import com.example.aspect.model.Person;
import com.example.aspect.service.aopintroductionfunction.DeclarativePersonMixinAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class AspectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AspectApplication.class, args);
    }

    @Bean
    public Person person() {
            var pers = new Person();
            pers.setId(-100L);
            return pers;
    }

    @Bean
    public DeclarativePersonMixinAspect personAspect() {
        return new DeclarativePersonMixinAspect();
    }

}
