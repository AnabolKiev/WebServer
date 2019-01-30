package com.anabol.webserver.util;

import com.anabol.webserver.entity.HttpMethod;
import com.anabol.webserver.entity.Request;
import com.anabol.webserver.exception.ServerException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.anabol.webserver.exception.ErrorType.*;

public class RequestParser {

    public static Request parseRequest(BufferedReader reader) {
        Request result = new Request();
        try {
            injectUriAndMethod(result, reader.readLine());
            injectHeaders(result, reader);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerException(BAD_REQUEST);
        }
    }

    static void injectUriAndMethod(Request request, String firstLine) {
        System.out.println(firstLine);
        String[] firstLineParts = firstLine.split("\\s+");                       // split first line by any number of spaces
        if (firstLineParts.length > 1 && HttpMethod.contains(firstLineParts[0])) {  // validation for request
            request.setHttpMethod(HttpMethod.getByName(firstLineParts[0]));
            request.setUri(firstLineParts[1]);
        } else {
            throw new ServerException(BAD_REQUEST);
        }
    }

    static void injectHeaders(Request request, BufferedReader reader) throws IOException {
        Map<String, String> headers = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null && !line.equals("")) { // read of request until the end
            //System.out.println(line);
            int index = line.indexOf(": ");
            if (index > -1) {
                String key = line.substring(0, index);
                String value = line.substring(index + 2);
                headers.put(key, value);
            }
            request.setHeaders(headers);
        }
    }

}
