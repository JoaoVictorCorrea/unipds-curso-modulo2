package br.com.projectspringmvc.events.controller;

import br.com.projectspringmvc.events.model.Session;
import br.com.projectspringmvc.events.service.ISessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SessionController {

    private final ISessionService sessionService;

    public SessionController(ISessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/sessions")
    public ResponseEntity<Session> addSession(@RequestBody Session session) {
        return ResponseEntity.ok(sessionService.addSession(session));
    }

    @GetMapping("/sessions")
    public ResponseEntity<List<Session>> getAllSessions() {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }

    @GetMapping("/sessions/{id}")
    public ResponseEntity<Session> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(sessionService.getSessionById(id));
    }
}
