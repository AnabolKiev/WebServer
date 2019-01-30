package com.anabol.webserver.util;

import com.anabol.webserver.exception.ErrorType;
import com.anabol.webserver.exception.ServerException;

import java.io.*;

import static com.anabol.webserver.exception.ErrorType.*;

public class ResponseWriter {
    private static final int BUFFER_SIZE = 1024;

    private static BufferedWriter writer;
    private static OutputStream outputStream;

    public static void writeSuccessResponse(InputStream content) {
        writeResponse("200 Ok", content);
    }

    public static void writeBadResponse(ErrorType errorType) throws IOException {
        writeResponse(errorType.getStatus(), null);
    }


    private static void writeResponse(String status, InputStream content) {
        try {
            writer.write("HTTP/1.0 " + status);
            writer.write("\n");
            writer.write("\n");
            writer.flush();
            // write content
            if (content != null) {
                int count;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((count = content.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, count);
                }
                outputStream.flush();
            }
            System.out.println("Response " + status + " sent");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServerException(INTERNAL_SERVER_ERROR);
        }
    }

    public static void setWriter(BufferedWriter writer) {
        ResponseWriter.writer = writer;
    }

    public static void setOutputStream(OutputStream outputStream) {
        ResponseWriter.outputStream = outputStream;
    }

}
