package com.javarush.cryptoanalyzer.ostapenko.exception;

public class NonWritingException extends ApplicationException{
    public NonWritingException() {
    }

    public NonWritingException(String message) {
        super(message);
    }

    public NonWritingException(String message, Throwable cause) {
        super(message, cause);
    }
}
