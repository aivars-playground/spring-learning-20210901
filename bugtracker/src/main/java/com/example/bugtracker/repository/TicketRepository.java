package com.example.bugtracker.repository;

import com.example.bugtracker.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}