package com.anabol.webserver;

import java.io.*;
import java.lang.invoke.WrongMethodTypeException;

public class RequestHandler {
    private final String webAppPath;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public RequestHandler(String webAppPath, BufferedReader reader, BufferedWriter writer) {
        this.webAppPath = webAppPath;
        this.reader = reader;
        this.writer = writer;
    }

    public void handle() throws IOException {
        try {
            ResponseWriter.setWriter(writer);
            Request request = RequestParser.parseRequest(reader);
            ResourceReader.setWebAppPath(webAppPath);
            String content = ResourceReader.readContent(request.getUri());
            ResponseWriter.writeSuccessResponse(content);
        } catch (IllegalArgumentException e) {
            ResponseWriter.writeBadRequestResponse();
        } catch (FileNotFoundException e) {
            ResponseWriter.writeNotFoundResponse();
        } catch (IOException e) {
            ResponseWriter.writeInternalServerErrorResponse();
        }
    }

}
