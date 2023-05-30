package common.model;


import java.io.Serializable;

public record Request (String method, Message content) implements Serializable {
    public String getMethod() {
        return method;
    }

    public Message getBody() {
        return content;
    }
}
