package com.anabol.webserver.exception;

public enum ErrorType {
    BAD_REQUEST ("400", "Bad Request"),
    NOT_FOUND ("404", "Not Found"),
    INTERNAL_SERVER_ERROR ("500", "Internal Server Error");


    private final String errorCode;
    private final String errorDescription;

    public String getStatus() {
        return errorCode + " " + errorDescription;
    }

    ErrorType(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
