package edu.bsuir.likeit.service;

public class ServiceException extends Exception {

    ServiceException() {
    }

    ServiceException(String message) {
        super(message);
    }

    ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    ServiceException(Throwable cause) {
        super(cause);
    }

    ServiceException(String message,
                     Throwable cause,
                     boolean enableSuppression,
                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
