package common.model;

import java.io.Serializable;

public class Message implements Serializable {
    private String sender;
    private String text;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


    @Override
    public String toString() {
        if (sender == null) {
            return text;
        }
        return sender + ": " + text;
    }
}
