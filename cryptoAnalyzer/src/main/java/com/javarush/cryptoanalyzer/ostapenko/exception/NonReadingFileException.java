package com.javarush.cryptoanalyzer.ostapenko.exception;

public class NonReadingFileException extends ApplicationException{

    public NonReadingFileException() {
    }

    public NonReadingFileException(String message) {
        super(message);
    }

    public NonReadingFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
