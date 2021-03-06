package com.anabol.webserver.entity;

public enum HttpMethod {
    POST("POST"),
    GET("GET");

    private final String name;

    public static HttpMethod getByName(String name) {
        HttpMethod[] httpMethods = HttpMethod.values();
        for (HttpMethod httpMethod : httpMethods) {
            if (httpMethod.getName().equalsIgnoreCase(name)) {
                return httpMethod;
            }
        }
        throw new IllegalArgumentException("No method for name " + name);
    }

    public static boolean contains(String name) {
        HttpMethod[] httpMethods = HttpMethod.values();
        for (HttpMethod httpMethod : httpMethods) {
            if (httpMethod.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    HttpMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
