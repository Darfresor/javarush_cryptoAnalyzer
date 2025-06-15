package com.javarush.cryptoanalyzer.ostapenko.exception;

public class ViewTypeException extends RuntimeException {
    public ViewTypeException(String message) {
        super(message);
    }

  public ViewTypeException(String message, Throwable cause) {
    super(message, cause);
  }

  public ViewTypeException(Throwable cause) {
    super(cause);
  }

  public ViewTypeException() {
  }
}
