package br.com.projectspringmvc.events.service;

import br.com.projectspringmvc.events.model.Session;

import java.util.List;

public interface ISessionService {
    Session addSession(Session session);
    Session getSessionById(Integer sessionId);
    List<Session> getAllSessions();
}
