package cz.muni.fi.pv243.model;

public class Message {

    private String name;
    private String text;

    private Message() {
    }

    public Message(String name, String text) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

}
