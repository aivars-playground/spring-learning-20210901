package com.example.bugtracker.repository;

import com.example.bugtracker.entity.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
}