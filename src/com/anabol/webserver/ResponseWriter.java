package com.anabol.webserver;

import java.io.BufferedWriter;
import java.io.IOException;

public class ResponseWriter {
    private static BufferedWriter writer;

    public static void setWriter(BufferedWriter writer) {
        ResponseWriter.writer = writer;
    }

    public static void writeSuccessResponse(String content) throws IOException {
        writeResponse("200 Ok", content);
    }

    public static void writeNotFoundResponse() throws IOException {
        writeResponse("404 Not Found", null);
    }

    public static void writeInternalServerErrorResponse() throws IOException {
        writeResponse("400 Bad Request", null);
    }

    public static void writeBadRequestResponse() throws IOException {
        writeResponse("500 Internal Server Error", null);
    }

    private static void writeResponse(String error, String content) throws IOException {
        writer.write("HTTP/1.0 " + error);
        writer.write("\n");
        writer.write("\n");
        if (content != null) {
            writer.write(content);
        }
        writer.flush();
        System.out.println("Response " + error + " sent");
    }

}
