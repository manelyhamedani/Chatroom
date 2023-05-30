package client;

import common.API;
import common.model.Message;
import common.model.Request;

import java.io.IOException;

public class Receiver extends Thread {
    private final Client client;

    public Receiver(Client client) {
        this.client = client;
    }


    private void receiveMsg() throws IOException, ClassNotFoundException {
        Request request;
        Message msg;
        while (true) {
            request = client.getRequest();
            if (request.getMethod().equals(API.EXIT)) {
                return;
            }
            msg = request.getBody();
            System.out.println(API.ANSI_GREEN + msg + API.ANSI_RESET);
        }

    }

    @Override
    public void run() {
        try {
            receiveMsg();
        }
        catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
