package br.com.dev.ia;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RagIngestionRunner implements CommandLineRunner {

    private final VectorStore vectorStore;
    private final Resource[] documents;

    public RagIngestionRunner(VectorStore vectorStore, @Value("${rag.documents.path}") Resource[] documents) {
        this.vectorStore = vectorStore;
        this.documents = documents;
    }

    @Override
    public void run(String... args) {
        TokenTextSplitter splitter = TokenTextSplitter.builder().build();
        for (Resource resource : documents) {
            List<Document> chunks = splitter.apply(new TextReader(resource).get());
            vectorStore.add(chunks);
        }
    }
}
