package server;

import common.API;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(API.PORT);
        Socket client;
        ExecutorService executorService = Executors.newCachedThreadPool();
        while (true) {
            client = serverSocket.accept();
            executorService.execute(new Handler(client));
        }
    }
}
