package br.com.projectspringmvc.events.repository;

import br.com.projectspringmvc.events.model.Conference;
import org.springframework.data.repository.ListCrudRepository;

public interface ConferenceRepository extends ListCrudRepository<Conference, Integer> {
}
