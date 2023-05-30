package client;

import common.API;
import common.model.Message;
import common.model.Request;

import java.io.IOException;
import java.util.Scanner;

public class Connector extends Thread {
    private final Client client;
    public Connector(Client client) {
        this.client = client;
    }

    private void handShake() throws IOException {
        System.out.println(API.ANSI_PURPLE + "********** Welcome to ChatRoom! **********\n");
        System.out.println("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String input;
        Request joinRequest;

        input = scanner.nextLine();
        Message joinMsg = new Message(input);

        joinRequest = new Request(API.JOIN, joinMsg);
        client.sendRequest(joinRequest);
        client.setUsername(input);
    }

    @Override
    public void run() {
        try {
            handShake();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
