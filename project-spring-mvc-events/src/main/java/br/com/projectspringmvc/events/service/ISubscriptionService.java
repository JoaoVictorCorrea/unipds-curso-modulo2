package br.com.projectspringmvc.events.service;

import br.com.projectspringmvc.events.model.Session;
import br.com.projectspringmvc.events.model.Subscription;
import br.com.projectspringmvc.events.model.User;

import java.util.List;

public interface ISubscriptionService {
    Subscription addSubscription(Subscription subscription);
    List<Subscription> getAllByUser(User user);
    List<Subscription> getAllBySession(Session session);
}
