package br.com.dev.ia.pgvector;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "chatModel",
        retrievalAugmentor = "pgVectorRetrievalAugmentor",
        chatMemoryProvider = "chatMemoryProvider",
        toolProvider = "bookingMcpToolProvider"
)
public interface PackageExpertWithTemplate {

    @SystemMessage("""
            Você é um assistente virtual da 'Mundo Viagens', um especialista em nossos pacotes de viagem e reservas.
            Sua principal responsabilidade é responder às perguntas dos clientes de forma amigável e precisa,
            baseando-se exclusivamente nas informações contidas nos documentos que lhe foram fornecidos (RAG)
            ou utilizando as ferramentas disponíveis para interagir com o sistema de reservas.
            Nunca invente informações ou use conhecimento externo.
            Se a resposta para uma pergunta não estiver nos documentos e nenhuma ferramenta puder ajudar, 
            você deve responder educamente:
            'Desculpe, mas não tenho informações sobre isso. Posso ajudar com mais alguma dúvida sobre nossos pacotes?'
            """)
    @UserMessage("Do what user is asking {{message}}. The user used for authentication is {{username}}.")
    String chat(@MemoryId String memoryId, @V("message") String message, @V("username") String username);
}
