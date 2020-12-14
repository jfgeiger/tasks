package com.example.tasks.handler;

import com.example.tasks.control.WebSocketSessions;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.text.MessageFormat;
import java.util.logging.Logger;

public class WebSocketHandler extends AbstractWebSocketHandler {

    private static final Logger LOGGER = Logger.getLogger(WebSocketHandler.class.getName());

    private final WebSocketSessions webSocketSessions;

    public WebSocketHandler(WebSocketSessions webSocketSessions) {
        this.webSocketSessions = webSocketSessions;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        final String id = session.getId();
        LOGGER.info(MessageFormat.format("WebSocket session opened: {0}", id));
        this.webSocketSessions.add(session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        final String id = session.getId();
        final String message = exception.getMessage();
        LOGGER.info(MessageFormat.format("WebSocket session error: {0}, {1}", id, message));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        final String id = session.getId();
        final String reasonPhrase = status.getReason();
        final int code = status.getCode();
        LOGGER.info(MessageFormat.format("WebSocket session closed: {0}, {1} ({2})", id, reasonPhrase, code));
        this.webSocketSessions.remove(session);
    }
}
