package client;

import common.model.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client {
    private String username;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private boolean exited = false;

    public Client(ObjectOutputStream output, ObjectInputStream input) {
        this.output = output;
        this.input = input;
    }


    public void sendRequest(Request request) throws IOException {
        synchronized (output) {
            output.writeObject(request);
        }
    }

    public Request getRequest() throws IOException, ClassNotFoundException {
        synchronized (input) {
            return ((Request) input.readObject());
        }
    }


    public boolean exited()  {
        return exited;
    }

    public void exit() {
        exited = true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
