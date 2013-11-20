package cz.muni.fi.pv243.websocket;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import cz.muni.fi.pv243.model.Message;
import cz.muni.fi.pv243.store.SessionStore;

@ServerEndpoint(value = "/socket/messages", encoders = { MessageEncoder.class })
public class MessagesSocket {

    private Logger log = Logger.getLogger(MessagesSocket.class.getName());

    @Inject
    private SessionStore sessions;

    @OnOpen
    public void onOpen(final Session session) {
        sessions.addSession(session);
    }

    @OnClose
    public void onClose(final Session session) {
        sessions.removeSession(session);
    }

    public void updateClients(@Observes Message message) {
        for (Session session : sessions.getAllSessions()) {
            if (session.isOpen()) {
                try {
                    session.getBasicRemote().sendObject(message);
                } catch (Exception e) {
                    log.log(Level.WARNING, e.getMessage(), e);
                }
            }
        }
    }
}
