package com.anabol.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class WebServer {
    private int port;
    private String webAppPath;

    public void setPort(int port) {
        this.port = port;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public static void main(String[] args) {
        WebServer webServer = new WebServer();
        webServer.setWebAppPath("resources/webapp");
        webServer.setPort(3000);
        webServer.start();
    }

    public void start() {
        System.out.println("WebServer started");
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                    RequestHandler requestHandler = new RequestHandler(webAppPath, reader, writer);
                    requestHandler.handle();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("WebServer stopped");
        }
    }
}
