package br.com.dev.ia;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travel")
public class TravelAgentController {

    private final TravelAgentAssistant assistant;
    private final EmbeddingStore<TextSegment> embeddingStore;
    private final EmbeddingModel embeddingModel;

    public TravelAgentController(TravelAgentAssistant assistant,
                                  EmbeddingStore<TextSegment> embeddingStore,
                                  EmbeddingModel embeddingModel){
        this.assistant = assistant;
        this.embeddingStore = embeddingStore;
        this.embeddingModel = embeddingModel;
    }

    @PostMapping
    public String ask(@RequestBody String question) {
        return assistant.chat(question);
    }

    @GetMapping("/debug/embeddings")
    public List<EmbeddingDebugResult> debugEmbeddings(@RequestParam String query) {
        Embedding queryEmbedding = embeddingModel.embed(query).content();
        EmbeddingSearchRequest request = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(20)
                .build();

        List<EmbeddingMatch<TextSegment>> matches = embeddingStore.search(request).matches();
        return matches.stream()
                .map(match -> new EmbeddingDebugResult(match.embeddingId(), match.score(), match.embedded().text()))
                .toList();
    }
}
