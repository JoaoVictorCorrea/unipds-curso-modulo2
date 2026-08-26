package br.com.dev.ia.easyrag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Easy RAG. Sem splitter configurado manualmente - o DocumentSplitterFactory
 * vindo via SPI do modulo langchain4j-easy-rag e usado por padrao. O
 * InMemoryEmbeddingStore e reindexado a cada start.
 */
@Configuration
public class EasyRagConfig {

    @Bean
    public EmbeddingStore<TextSegment> easyRagEmbeddingStore(EmbeddingModel embeddingModel,
                                                              @Value("${rag.documents.path}") String documentsPath) {
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        Document document = FileSystemDocumentLoader.loadDocument(documentsPath);
        document.metadata().put("type", "packages");

        EmbeddingStoreIngestor.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build()
                .ingest(document);

        return embeddingStore;
    }

    @Bean
    public ContentRetriever easyRagContentRetriever(@Qualifier("easyRagEmbeddingStore") EmbeddingStore<TextSegment> embeddingStore,
                                                     EmbeddingModel embeddingModel) {
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(3)
                .minScore(0.5)
                .build();
    }
}
