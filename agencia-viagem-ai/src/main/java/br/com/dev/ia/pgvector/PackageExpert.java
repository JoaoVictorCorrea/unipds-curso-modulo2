package br.com.dev.ia.pgvector;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "chatModel",
        retrievalAugmentor = "pgVectorRetrievalAugmentor",
        chatMemoryProvider = "chatMemoryProvider"
)
public interface PackageExpert {

    @SystemMessage("""
            Você é um assistente virtual da 'Mundo Viagens', um especialista em nossos pacotes de viagem.
            Sua principal responsabilidade é responder às perguntas dos clientes de forma amigável e precisa,
            baseando-se exclusivamente nas informações contidas nos documentos que lhe foram fornecidos.
            Nunca invente informações ou use conhecimento externo.
            Se a resposta para uma pergunta não estiver nos documentos, você deve responder educamente:
            'Desculpe, mas não tenho informações sobre isso. Posso ajudar com mais alguma dúvida sobre nossos pacotes?'
            """)
    String chat(@MemoryId String memoryId, @UserMessage String userMessage);
}
