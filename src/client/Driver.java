package client;

import common.API;
import common.model.Request;

import java.io.*;
import java.net.Socket;

public class Driver {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket server = new Socket(API.SERVER_ADDRESS, API.PORT);

        ObjectOutputStream outputStream = new ObjectOutputStream(server.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(server.getInputStream());

        Client client = new Client(outputStream, inputStream);

        Connector connector = new Connector(client);
        connector.start();

        connector.join();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Request request = new Request(API.EXIT, null);
            try {
                client.sendRequest(request);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }));

        Receiver receiver = new Receiver(client);
        Sender sender = new Sender(client);

        receiver.start();
        sender.start();

    }

}
