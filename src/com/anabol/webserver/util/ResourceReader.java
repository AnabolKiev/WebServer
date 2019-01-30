package com.anabol.webserver.util;

import com.anabol.webserver.exception.*;

import java.io.*;

import static com.anabol.webserver.exception.ErrorType.*;

public class ResourceReader {
    private static String webAppPath;

    public static InputStream readContent(String uri) {
        try {
            File file = new File(webAppPath, uri);
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new ServerException(NOT_FOUND);
        }
    }

    public static void setWebAppPath(String webAppPath) {
        ResourceReader.webAppPath = webAppPath;
    }
}
