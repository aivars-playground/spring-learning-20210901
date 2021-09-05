package com.example.bugtracker.resolver;

import com.example.bugtracker.entity.Application;
import com.example.bugtracker.repository.ApplicationRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    private final ApplicationRepository applicationRepository;

    public Query(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Iterable<Application> findAllApplications() {
        return applicationRepository.findAll();
    }

    public Long countApplications() {
        return applicationRepository.count();
    }
}
