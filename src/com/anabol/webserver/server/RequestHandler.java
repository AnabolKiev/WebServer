package com.anabol.webserver.server;

import com.anabol.webserver.exception.ServerException;
import com.anabol.webserver.util.RequestParser;
import com.anabol.webserver.util.ResourceReader;
import com.anabol.webserver.util.ResponseWriter;
import com.anabol.webserver.entity.Request;

import java.io.*;

import static com.anabol.webserver.exception.ErrorType.INTERNAL_SERVER_ERROR;

public class RequestHandler {
    private final String webAppPath;
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private final OutputStream outputStream;

    public RequestHandler(String webAppPath, BufferedReader reader, BufferedWriter writer, OutputStream outputStream) {
        this.webAppPath = webAppPath;
        this.reader = reader;
        this.writer = writer;
        this.outputStream = outputStream;
    }

    public void handle() throws IOException {
        ResponseWriter.setWriter(writer);

        try {
            Request request = RequestParser.parseRequest(reader);
            ResourceReader.setWebAppPath(webAppPath);
            InputStream content = ResourceReader.readContent(request.getUri());
            ResponseWriter.setOutputStream(outputStream);
            ResponseWriter.writeSuccessResponse(content);
        } catch (ServerException e) {  //
            e.printStackTrace();
            ResponseWriter.writeBadResponse(e.getErrorType());
        } catch (Exception e) {
            e.printStackTrace();
            ResponseWriter.writeBadResponse(INTERNAL_SERVER_ERROR);
        }

    }

}
