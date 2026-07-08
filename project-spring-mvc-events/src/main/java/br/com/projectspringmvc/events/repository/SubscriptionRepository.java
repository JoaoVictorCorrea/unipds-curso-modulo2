package br.com.projectspringmvc.events.repository;

import br.com.projectspringmvc.events.model.Session;
import br.com.projectspringmvc.events.model.Subscription;
import br.com.projectspringmvc.events.model.SubscriptionID;
import br.com.projectspringmvc.events.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface SubscriptionRepository extends ListCrudRepository<Subscription, SubscriptionID> {
    List<Subscription> findByIdUser(User user);
    List<Subscription> findByIdSession(Session session);
}
