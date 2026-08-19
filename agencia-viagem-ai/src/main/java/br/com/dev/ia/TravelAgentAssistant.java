package br.com.dev.ia;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import org.springframework.stereotype.Component;

@Component
public class TravelAgentAssistant {

    interface Assistant {
        String chat(String message);
    }

    private final Assistant assistant;

    public TravelAgentAssistant(ChatModel chatModel, ContentRetriever contentRetriever) {
        this.assistant = AiServices.builder(Assistant.class)
                .chatModel(chatModel)
                .contentRetriever(contentRetriever)
                .build();
    }

    public String chat(String message) {
        return assistant.chat(message);
    }
}