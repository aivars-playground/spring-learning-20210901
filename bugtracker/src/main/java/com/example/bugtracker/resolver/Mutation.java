package com.example.bugtracker.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.bugtracker.entity.Application;
import com.example.bugtracker.repository.ApplicationRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final ApplicationRepository applicationRepository;

    public Mutation(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Application newApplication(String name, String description, String owner) {
        return applicationRepository.save(
                new Application(name,description,owner)
        );
    }

    public Boolean deleteApplication(Integer id) {
        applicationRepository.deleteById(id.longValue());
        return true;
    }

    public Application updateApplicationOwner(String owner, Integer id) {
        Optional<Application> maybeApp = applicationRepository.findById(id.longValue());
        if (maybeApp.isPresent()) {
            var app = maybeApp.get();
            app.setOwner(owner);
            return applicationRepository.save(app);
        } else {
            throw new AppNotFound("app does not exists", id.longValue());
        }
    }
}
