package com.example.bugtracker.repository;

import com.example.bugtracker.entity.Release;
import org.springframework.data.repository.CrudRepository;

public interface ReleaseRepository extends CrudRepository<Release, Long> {
}