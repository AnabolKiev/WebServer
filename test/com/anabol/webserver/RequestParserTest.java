package com.anabol.webserver;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class RequestParserTest {

    @Test
    public void injectHeadersTest() {
        Request request = new Request();
        List<String> list = new ArrayList<>();
        list.add("GET /index.html HTTP/1.1");
        list.add("Host: localhost:3000");
        RequestParser.injectHeaders(request, list);
        assertEquals(1, request.getHeaders().size());
        assertEquals("localhost:3000", request.getHeaders().get("Host"));
    }

    @Test
    public void injectUriAndMethod() {
        Request request = new Request();
        String[] firstLineParts = "GET /index.html HTTP/1.1".split("\\s+");
        RequestParser.injectUriAndMethod(request, firstLineParts);
        assertEquals(HttpMethod.GET, request.getHttpMethod());
        assertEquals("/index.html", request.getUri());
    }

    @Test(expected = IllegalArgumentException.class)
    public void injectWrongMethod() {
        Request request = new Request();
        String[] firstLineParts = "SET /index.html HTTP/1.1".split("\\s+");
        RequestParser.injectUriAndMethod(request, firstLineParts);
    }

}
