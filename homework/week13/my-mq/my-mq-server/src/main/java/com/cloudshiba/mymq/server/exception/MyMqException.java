package com.cloudshiba.mymq.server.exception;

public class MyMqException extends RuntimeException {
    public MyMqException(String message) {
        super(message);
    }

    public MyMqException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyMqException(Throwable cause) {
        super(cause);
    }
}
