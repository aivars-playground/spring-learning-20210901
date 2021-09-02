package org.example.repositories;

import org.example.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Integer> {
}