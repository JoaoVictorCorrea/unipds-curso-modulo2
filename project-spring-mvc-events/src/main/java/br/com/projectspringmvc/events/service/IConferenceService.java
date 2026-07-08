package br.com.projectspringmvc.events.service;

import br.com.projectspringmvc.events.model.Conference;

import java.util.List;

public interface IConferenceService {
    Conference addConference(Conference conference);
    Conference getConferenceById(Integer conferenceId);
    List<Conference> getAllConferences();
}
