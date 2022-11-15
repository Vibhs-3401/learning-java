package com.register.learning.exception;


import java.util.Date;

public class ExceptionResponse {

    private Date timestamp;

    private Boolean status;

    private String message;

    private String data;

    public ExceptionResponse(Date timestamp, Boolean status, String message, String data) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }
}
