package com.anabol.network;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        try (Socket socket = new Socket("127.0.0.1", 3000);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))){

            while (true) {
                String message = consoleInput.readLine();
                out.write(message);
                out.newLine();
                out.flush();

                String response = in.readLine();
                System.out.println(response);
            }
        }
    }
}
