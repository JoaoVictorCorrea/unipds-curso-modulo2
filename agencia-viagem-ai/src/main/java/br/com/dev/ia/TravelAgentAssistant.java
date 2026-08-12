package br.com.dev.ia;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

@Component
public class TravelAgentAssistant {

    private final ChatClient chatClient;

    public TravelAgentAssistant(ChatClient.Builder builder, VectorStore vectorStore){
        this.chatClient = builder.defaultAdvisors(RetrievalAugmentationAdvisor.builder()
                                    .documentRetriever(VectorStoreDocumentRetriever.builder()
                                        .vectorStore(vectorStore).build())
                                    .build())
                                 .build();
    }

    public String chat(String message){
        return chatClient.prompt().user(message).call().content();
    }
}