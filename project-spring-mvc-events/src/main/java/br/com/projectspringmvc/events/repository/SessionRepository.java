package br.com.projectspringmvc.events.repository;

import br.com.projectspringmvc.events.model.Session;
import org.springframework.data.repository.ListCrudRepository;

public interface SessionRepository extends ListCrudRepository<Session, Integer> {
}
