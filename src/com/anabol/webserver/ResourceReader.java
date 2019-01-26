package com.anabol.webserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ResourceReader {
    private static String webAppPath;

    public static void setWebAppPath(String webAppPath) {
        ResourceReader.webAppPath = webAppPath;
    }

    public static String readContent(String uri) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(webAppPath + uri))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }
    }
}
