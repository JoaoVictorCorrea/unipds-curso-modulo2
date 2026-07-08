package br.com.projectspringmvc.events.service;

import br.com.projectspringmvc.events.model.Session;
import br.com.projectspringmvc.events.model.Subscription;
import br.com.projectspringmvc.events.model.User;
import br.com.projectspringmvc.events.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionServiceImpl implements ISubscriptionService{

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Subscription addSubscription(Subscription subscription) {
        subscription.setCreatedAt(LocalDateTime.now());
        subscription.setUniqueID(UUID.randomUUID().toString());
        return subscriptionRepository.save(subscription);
    }

    @Override
    public List<Subscription> getAllByUser(User user) {
        return subscriptionRepository.findByIdUser(user);
    }

    @Override
    public List<Subscription> getAllBySession(Session session) {
        return subscriptionRepository.findByIdSession(session);
    }
}
