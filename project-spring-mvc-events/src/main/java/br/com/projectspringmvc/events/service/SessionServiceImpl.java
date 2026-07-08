package br.com.projectspringmvc.events.service;

import br.com.projectspringmvc.events.model.Session;
import br.com.projectspringmvc.events.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements ISessionService {

    private final SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Session addSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session getSessionById(Integer sessionId) {
        return sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found with id: " + sessionId));
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }
}
