package com.anabol.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        System.out.println("EchoServer started");

        try (ServerSocket serverSocket = new ServerSocket(3000)) {
            while (true) {
                Socket socket = serverSocket.accept();
                EchoTask task = new EchoTask(socket);
                Thread thread = new Thread(task);
                thread.start();
            }
        }
    }
}