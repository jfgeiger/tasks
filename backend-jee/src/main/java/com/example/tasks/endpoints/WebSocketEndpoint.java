package com.example.tasks.endpoints;

import com.example.tasks.control.WebSocketSessions;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.text.MessageFormat;
import java.util.logging.Logger;

@Stateless
@ServerEndpoint("/ws")
public class WebSocketEndpoint {

    private static final Logger LOGGER = Logger.getLogger(WebSocketEndpoint.class.getName());

    @EJB
    private WebSocketSessions webSocketSessions;

    @OnOpen
    public void onOpen(final Session session) {
        final String id = session.getId();
        LOGGER.info(MessageFormat.format("WebSocket session opened: {0}", id));
        this.webSocketSessions.add(session);
    }

    @OnError
    public void onError(final Session session, final Throwable error) {
        final String id = session.getId();
        final String message = error.getMessage();
        LOGGER.info(MessageFormat.format("WebSocket session error: {0}, {1}", id, message));
    }

    @OnClose
    public void onClose(final Session session, final CloseReason closeReason) {
        final String id = session.getId();
        final String reasonPhrase = closeReason.getReasonPhrase();
        final CloseReason.CloseCode closeCode = closeReason.getCloseCode();
        LOGGER.info(MessageFormat.format("WebSocket session closed: {0}, {1} ({2})", id, reasonPhrase, closeCode));
        this.webSocketSessions.remove(session);
    }
}
