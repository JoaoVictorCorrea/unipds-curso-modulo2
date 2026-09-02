package br.com.dev.ia.pgvector;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * PgVector. Persistencia real em Postgres, splitter configuravel via
 * properties e memoria de conversa por sessao (usada pelo PackageExpert).
 */
@Configuration
public class PgVectorConfig {

    @Bean
    public EmbeddingStore<TextSegment> pgVectorEmbeddingStore(EmbeddingModel embeddingModel,
                                                               @Value("${rag.documents.path}") String documentsPath,
                                                               @Value("${rag.splitter.max-segment-size}") int maxSegmentSize,
                                                               @Value("${rag.splitter.max-overlap-size}") int maxOverlapSize,
                                                               @Value("${langchain4j.pgvector.host}") String host,
                                                               @Value("${langchain4j.pgvector.port}") int port,
                                                               @Value("${langchain4j.pgvector.database}") String database,
                                                               @Value("${langchain4j.pgvector.user}") String user,
                                                               @Value("${langchain4j.pgvector.password}") String password,
                                                               @Value("${langchain4j.pgvector.table}") String table,
                                                               @Value("${langchain4j.pgvector.dimension}") int dimension,
                                                               @Value("${langchain4j.pgvector.drop-table-first}") boolean dropTableFirst) {

        EmbeddingStore<TextSegment> embeddingStore = PgVectorEmbeddingStore.builder()
                .host(host)
                .port(port)
                .database(database)
                .user(user)
                .password(password)
                .table(table)
                .dimension(dimension)
                .dropTableFirst(dropTableFirst)
                .build();

        Document document = FileSystemDocumentLoader.loadDocument(documentsPath);
        document.metadata().put("type", "packages");

        EmbeddingStoreIngestor.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .documentSplitter(DocumentSplitters.recursive(maxSegmentSize, maxOverlapSize))
                .build()
                .ingest(document);

        return embeddingStore;
    }

    @Bean
    public RetrievalAugmentor pgVectorRetrievalAugmentor(@Qualifier("pgVectorEmbeddingStore") EmbeddingStore<TextSegment> embeddingStore,
                                                         @Value("${rag.retriever.min-score}") double minScore,
                                                         EmbeddingModel embeddingModel) {
        return DefaultRetrievalAugmentor.builder()
                .contentRetriever(EmbeddingStoreContentRetriever.builder()
                        .embeddingStore(embeddingStore)
                        .embeddingModel(embeddingModel)
                        .maxResults(5)
                        .minScore(minScore)
                        .build())
                .build();
    }

    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(20)
                .chatMemoryStore(new InMemoryChatMemoryStore())
                .build();
    }
}
