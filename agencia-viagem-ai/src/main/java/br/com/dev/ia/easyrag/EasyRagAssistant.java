package br.com.dev.ia.easyrag;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EasyRagAssistant {

    interface Assistant {
        String chat(String message);
    }

    private final Assistant assistant;

    public EasyRagAssistant(ChatModel chatModel, @Qualifier("easyRagContentRetriever") ContentRetriever contentRetriever) {
        this.assistant = AiServices.builder(Assistant.class)
                .chatModel(chatModel)
                .contentRetriever(contentRetriever)
                .build();
    }

    public String chat(String message) {
        return assistant.chat(message);
    }
}
