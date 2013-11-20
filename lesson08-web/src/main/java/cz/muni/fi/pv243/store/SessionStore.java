package cz.muni.fi.pv243.store;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

@ApplicationScoped
public class SessionStore {


    private List<Session> sessions = Collections.synchronizedList(new LinkedList<Session>());

    public List<Session> getAllSessions() {
        return Collections.unmodifiableList(sessions);
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

}
