package br.com.projectspringmvc.events.repository;

import br.com.projectspringmvc.events.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
