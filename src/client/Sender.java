package client;

import common.API;
import common.model.Message;
import common.model.Request;

import java.io.IOException;
import java.util.Scanner;

public class Sender extends Thread {
    private final Client client;
    private final Scanner scanner = new Scanner(System.in);

    public Sender(Client client) {
        this.client = client;
    }


    private void sendMsg() throws IOException {
        while (!client.exited()) {
            String input = scanner.nextLine();
            Message msg = new Message(input);
            msg.setSender(client.getUsername());
            Request request;
            if (!input.equals("#exit")) {
                request = new Request(API.SEND_MSG, msg);
                client.sendRequest(request);
            }
            else {
                request = new Request(API.EXIT, null);
                client.sendRequest(request);
                client.exit();
            }
        }

    }

    @Override
    public void run() {

        try {
            sendMsg();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
