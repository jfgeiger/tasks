package com.example.tasks.handler;

import com.example.tasks.control.WebSocketSessions;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    private final WebSocketSessions webSocketSessions;

    public WebSocketConfiguration(WebSocketSessions webSocketSessions) {
        this.webSocketSessions = webSocketSessions;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        final WebSocketHandler webSocketHandler = new WebSocketHandler(this.webSocketSessions);
        registry.addHandler(webSocketHandler, "/ws").setAllowedOrigins("*");
    }
}
