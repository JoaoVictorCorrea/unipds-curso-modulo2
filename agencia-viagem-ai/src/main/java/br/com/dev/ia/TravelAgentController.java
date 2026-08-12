package br.com.dev.ia;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travel")
public class TravelAgentController {

    private final TravelAgentAssistant assistant;

    public TravelAgentController(TravelAgentAssistant assistant){
        this.assistant = assistant;
    }

    @PostMapping
    public String ask(@RequestBody String question) {
        return assistant.chat(question);
    }
}
