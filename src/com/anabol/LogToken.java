package com.anabol;

import com.anabol.webserver.entity.HttpMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogToken {
    private LocalDateTime time;
    private HttpMethod method;
    private String message;

    public LogToken(String line) {
        int startIndexOfDate = line.indexOf(91) + 1; // 91 = [
        int finalIndexOfDate = line.indexOf(93);     // 93 = ]
        String dateTime = line.substring(startIndexOfDate, finalIndexOfDate);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        time = LocalDateTime.parse(dateTime, dateTimeFormatter);

        int startIndexOfMethod = line.indexOf(34, finalIndexOfDate) + 1;  // 34 = "
        int finalIndexOfMethod = line.indexOf(32, startIndexOfMethod);    // 32 = space
        String methodString = line.substring(startIndexOfMethod, finalIndexOfMethod);
        method = HttpMethod.getByName(methodString);

        int startIndexOfMessage = finalIndexOfMethod + 1;
        int finalIndexOfMessage = line.indexOf(34, startIndexOfMessage);  // 34 = "
        message = line.substring(startIndexOfMessage, finalIndexOfMessage);
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LogToken{" +
                "time=" + time +
                ", method=" + method +
                ", message='" + message + '\'' +
                '}';
    }
}
