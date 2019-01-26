package com.anabol.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestParser {

    public static Request parseRequest(BufferedReader reader) throws IOException {
        Request result = new Request();
        String line;
        List<String> lines = new ArrayList<>();
        while ((line = reader.readLine()) != null && !line.equals("")) { // read of request into a list
            System.out.println(line);
            lines.add(line);
        }

        if (!lines.isEmpty()) {
            String[] firstLineParts = lines.get(0).split("\\s+");                       // split first line by any number of spaces
            if (firstLineParts.length > 1 && HttpMethod.contains(firstLineParts[0])) {  // validation fo request
                injectUriAndMethod(result, firstLineParts);
                injectHeaders(result, lines);
            } else {
                throw new IllegalArgumentException("Request isn't recognized");
            }
        }
        return result;
    }

    static void injectUriAndMethod(Request request, String[] firstLineParts) {
        request.setHttpMethod(HttpMethod.getByName(firstLineParts[0]));
        request.setUri(firstLineParts[1]);
    }

    static void injectHeaders(Request request, List<String> lines) {
        Map<String, String> headers = new HashMap<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            int index = line.indexOf(": ");
            if (index > -1) {
                String key = line.substring(0, index);
                String value = line.substring(index + 2);
                headers.put(key, value);
            }
        }
        request.setHeaders(headers);
    }

}
