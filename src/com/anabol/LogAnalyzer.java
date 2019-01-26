package com.anabol;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogAnalyzer {

    public static List<LogToken> filter(String path, LocalDateTime timeFrom, LocalDateTime timeTo) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line;
        List<LogToken> result = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            LogToken logToken = new LogToken(line);
            if (logToken.getTime().isAfter(timeFrom) && logToken.getTime().isBefore(timeTo)) {
                result.add(new LogToken(line));
            }
        }
        return result;
    }
}
