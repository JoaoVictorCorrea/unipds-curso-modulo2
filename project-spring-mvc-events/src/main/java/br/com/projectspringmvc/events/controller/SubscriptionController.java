package br.com.projectspringmvc.events.controller;

import br.com.projectspringmvc.events.model.Session;
import br.com.projectspringmvc.events.model.Subscription;
import br.com.projectspringmvc.events.model.User;
import br.com.projectspringmvc.events.service.ISubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubscriptionController {
    private final ISubscriptionService subscriptionService;

    public SubscriptionController(ISubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/subscriptions")
    public ResponseEntity<Subscription> addSubscription(@RequestBody Subscription subscription) {
        return ResponseEntity.ok(subscriptionService.addSubscription(subscription));
    }

    @GetMapping("/subscriptions/user/{userId}")
    public ResponseEntity<List<Subscription>> getByUser(@PathVariable Integer userId){
        User user = new User();
        user.setUserId(userId);
        return ResponseEntity.ok(subscriptionService.getAllByUser(user));
    }

    @GetMapping("/subscriptions/session/{sessionId}")
    public ResponseEntity<List<Subscription>> getBySession(@PathVariable Integer sessionId){
        Session session = new Session();
        session.setIdSession(sessionId);
        return ResponseEntity.ok(subscriptionService.getAllBySession(session));
    }
}
