package cz.muni.fi.pv243.rest;

import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cz.muni.fi.pv243.model.Message;
import cz.muni.fi.pv243.store.MessageStore;

@Path("/messages")
public class MessagesEndpoint {

    @Inject
    private MessageStore messages;

    @Inject
    private Event<Message> messageEvent;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getMessages() {
        return messages.getMessages();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addMessage(Message message) {
        messages.addMessage(message);
        messageEvent.fire(message);
    }
}
