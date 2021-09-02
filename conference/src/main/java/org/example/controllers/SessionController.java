package org.example.controllers;

import org.example.models.Session;
import org.example.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

    private final SessionRepository sessionRepository;

    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED) //just some semantics... 200 ok or 202 accepted...
    public Session create(@RequestBody Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    @GetMapping("{id}")
    public Session read(@PathVariable Integer id) {
        return sessionRepository.getById(id);
    }

    @PutMapping("{id}")
    public Session update(@PathVariable Integer id, @RequestBody Session session) {
        var existingSession = sessionRepository.getById(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        sessionRepository.deleteById(id);
    }
}
