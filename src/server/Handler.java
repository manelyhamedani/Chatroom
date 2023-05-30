package server;

import client.Client;
import common.API;
import common.model.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Handler implements Runnable {
    private static final ArrayList<Handler> clientHandlers = new ArrayList<>();
    private boolean exit = false;
    private final Client client;

    public Handler(Socket client) throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(client.getInputStream());
        this.client = new Client(output, input);
        clientHandlers.add(this);
    }

    public Client getClient() {
        return client;
    }
    @Override
    public void run() {
        try {
            while (!exit) {
                Request request = client.getRequest();
                handle(request);
            }
        }
        catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
        }
    }

    private void handle(Request request) throws IOException {
        switch (request.getMethod()) {
            case API.JOIN -> handleJoin(request);
            case API.SEND_MSG -> handleSendMsg(request);
            case API.EXIT -> handleExit();
        }
    }

    private void handleExit() throws IOException {
        Message exitMsg = new Message(API.ANSI_RED + client.getUsername() + " left the chatroom!\n" + API.ANSI_RESET);
        client.sendRequest(new Request(API.EXIT, null));
        for (Handler h : clientHandlers) {
            sendMsgTo(exitMsg, h);
        }
        clientHandlers.remove(this);
        exit = true;
    }

    private void handleJoin(Request request) throws IOException {
        String username = request.getBody().getText();
        client.setUsername(username);

        Message msg = new Message( API.ANSI_BLUE + username + " joined chatroom!\n" + API.ANSI_RESET);

        for (Handler h : clientHandlers) {
           sendMsgTo(msg, h);
        }
    }

    private void handleSendMsg(Request request) throws IOException {
        Message msg = request.getBody();

        for (Handler h : clientHandlers) {
            if (h != this) {
                sendMsgTo(msg, h);
            }
        }
    }

    private void sendMsgTo(Message msg, Handler handler) throws IOException {
        handler.getClient().sendRequest(new Request(API.SEND_MSG, msg));
    }

}
