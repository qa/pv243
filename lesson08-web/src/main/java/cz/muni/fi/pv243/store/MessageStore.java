package cz.muni.fi.pv243.store;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import cz.muni.fi.pv243.model.Message;

@ApplicationScoped
public class MessageStore {


    private List<Message> messages = Collections.synchronizedList(new LinkedList<Message>());

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

}
