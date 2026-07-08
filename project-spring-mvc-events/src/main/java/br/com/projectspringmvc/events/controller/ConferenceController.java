package br.com.projectspringmvc.events.controller;

import br.com.projectspringmvc.events.model.Conference;
import br.com.projectspringmvc.events.service.IConferenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConferenceController {

    private final IConferenceService conferenceService;

    public ConferenceController(IConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @PostMapping("/conferences")
    public ResponseEntity<Conference> addSession(@RequestBody Conference conference) {
        return ResponseEntity.ok(conferenceService.addConference(conference));
    }

    @GetMapping("/conferences")
    public ResponseEntity<List<Conference>> getAllConferences(){
        return ResponseEntity.ok(conferenceService.getAllConferences());
    }

    @GetMapping("/conferences/{conferenceId}")
    public ResponseEntity<Conference> getConferenceById(@PathVariable Integer conferenceId){
        return ResponseEntity.ok(conferenceService.getConferenceById(conferenceId));
    }
}
