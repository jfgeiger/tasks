package com.example.tasks.control;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

@Component
@Scope("singleton")
public class WebSocketSessions {

    private static final Logger LOGGER = Logger.getLogger(WebSocketSessions.class.getName());

    private final ConcurrentMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @PreDestroy
    public void preDestroy() {
        for (final WebSocketSession session : this.sessions.values()) {
            try {
                session.close();
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    public void add(final WebSocketSession session) {
        final String id = session.getId();
        this.sessions.put(id, session);
    }

    public void remove(final WebSocketSession session) {
        final String id = session.getId();
        this.sessions.remove(id);
    }

    public void send(final String message) {
        for (final WebSocketSession session : this.sessions.values()) {
            final String id = session.getId();
            final TextMessage textMessage = new TextMessage(message);

            try {
                session.sendMessage(textMessage);
            } catch (IOException | IllegalStateException e) {
                LOGGER.severe(e.getMessage());
            }

            LOGGER.info(MessageFormat.format("Sent ''{0}'' to {1}", message, id));
        }
    }
}
