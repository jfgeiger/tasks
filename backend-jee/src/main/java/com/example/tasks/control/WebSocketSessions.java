package com.example.tasks.control;

import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Singleton
public class WebSocketSessions {

    private static final Logger LOGGER = Logger.getLogger(WebSocketSessions.class.getName());

    private final Map<String, Session> sessions = new HashMap<>();

    @PreDestroy
    public void preDestroy() {
        for (final Session session : this.sessions.values()) {
            try {
                session.close();
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    @Lock(LockType.WRITE)
    public void add(final Session session) {
        final String id = session.getId();
        this.sessions.put(id, session);
    }

    @Lock(LockType.WRITE)
    public void remove(final Session session) {
        final String id = session.getId();
        this.sessions.remove(id);
    }

    @Lock(LockType.READ)
    public void send(final String message) {
        for (final Session session : this.sessions.values()) {
            final String id = session.getId();
            final RemoteEndpoint.Basic basicRemote = session.getBasicRemote();

            try {
                basicRemote.sendText(message);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
            }

            LOGGER.info(MessageFormat.format("Sent ''{0}'' to {1}", message, id));
        }
    }
}
