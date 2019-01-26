package com.anabol.network;

import java.io.*;
import java.net.Socket;

class EchoTask implements Runnable {
    private final Socket socket;

    public EchoTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            System.out.println("Socket created");
            String message;
            while (!(message = in.readLine()).isEmpty()) {
                System.out.println("Message received: " + message);

                String response = "Echo " + message;
                out.write(response);
                out.newLine();
                out.flush();
                System.out.println("Message responded: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
