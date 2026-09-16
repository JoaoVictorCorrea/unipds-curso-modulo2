package br.com.dev.ia.mcp;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.http.HttpMcpTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpConfig {

    @Bean
    public McpTransport bookingMcpTransport(@Value("${mcp.booking-server.sse-url}") String sseUrl,
                                            @Value("${mcp.booking-server.log-requests}") boolean logRequests,
                                            @Value("${mcp.booking-server.log-responses}") boolean logResponses) {
        return HttpMcpTransport.builder()
                .sseUrl(sseUrl)
                .logRequests(logRequests)
                .logResponses(logResponses)
                .build();
    }

    @Bean
    public McpClient bookingMcpClient(McpTransport bookingMcpTransport) {
        return DefaultMcpClient.builder()
                .key("booking-server")
                .transport(bookingMcpTransport)
                .build();
    }

    @Bean
    public McpToolProvider bookingMcpToolProvider(McpClient bookingMcpClient) {
        return McpToolProvider.builder()
                .mcpClients(bookingMcpClient)
                .build();
    }
}
