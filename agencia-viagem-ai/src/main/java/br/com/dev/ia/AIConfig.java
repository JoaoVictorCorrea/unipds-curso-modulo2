package br.com.dev.ia;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * Infraestrutura compartilhada entre v1 (Easy RAG) e v2 (PgVector): a conexao
 * com o Ollama. O que difere entre as duas versoes (embedding store, splitter,
 * memoria de conversa) fica em br.com.dev.ia.easyrag e br.com.dev.ia.pgvector
 */
@Configuration
public class AIConfig {

    @Bean
    public ChatModel chatModel(@Value("${langchain4j.ollama.base-url}") String baseUrl,
                               @Value("${langchain4j.ollama.chat-model}") String modelName,
                               @Value("${langchain4j.ollama.timeout-seconds}") long timeoutSeconds,
                               @Value("${langchain4j-log-requests}") boolean logRequests,
                               @Value("${langchain4j-log-responses}") boolean logResponses) {
        return OllamaChatModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .timeout(Duration.ofSeconds(timeoutSeconds))
                .logRequests(logRequests)
                .logResponses(logResponses)
                .build();
    }

    @Bean
    public EmbeddingModel embeddingModel(@Value("${langchain4j.ollama.base-url}") String baseUrl,
                                         @Value("${langchain4j.ollama.embedding-model}") String modelName) {
        return OllamaEmbeddingModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .build();
    }
}
