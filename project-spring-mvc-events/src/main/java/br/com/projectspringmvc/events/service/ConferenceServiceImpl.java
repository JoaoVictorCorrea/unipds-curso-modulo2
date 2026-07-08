package br.com.projectspringmvc.events.service;

import br.com.projectspringmvc.events.exception.NotFoundException;
import br.com.projectspringmvc.events.model.Conference;
import br.com.projectspringmvc.events.repository.ConferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceServiceImpl implements IConferenceService {

    private final ConferenceRepository conferenceRepository;

    public ConferenceServiceImpl(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @Override
    public Conference addConference(Conference conference) {
        return conferenceRepository.save(conference);
    }

    @Override
    public Conference getConferenceById(Integer conferenceId) {
        return conferenceRepository.findById(conferenceId).orElseThrow(() -> new NotFoundException("Conference not found with id: " + conferenceId));
    }

    @Override
    public List<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }
}
