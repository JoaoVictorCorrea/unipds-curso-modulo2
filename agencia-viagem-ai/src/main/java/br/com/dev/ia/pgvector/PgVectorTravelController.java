package br.com.dev.ia.pgvector;

import br.com.dev.ia.EmbeddingDebugResult;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travel/pg-vector")
public class PgVectorTravelController {

    private final PackageExpert expert;
    private final EmbeddingStore<TextSegment> embeddingStore;
    private final EmbeddingModel embeddingModel;

    public PgVectorTravelController(PackageExpert expert,
                                    @Qualifier("pgVectorEmbeddingStore") EmbeddingStore<TextSegment> embeddingStore,
                                    EmbeddingModel embeddingModel) {
        this.expert = expert;
        this.embeddingStore = embeddingStore;
        this.embeddingModel = embeddingModel;
    }

    @PostMapping
    public String ask(@RequestBody String question) {
        return expert.chat("session-123", question);
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
