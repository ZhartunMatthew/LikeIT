package edu.bsuir.likeit.connectionpool;

/**
 * Created by Victoria on 25.01.2017.
 */
public class ConnectionPoolException extends Exception {
    ConnectionPoolException() {
    }

    ConnectionPoolException(String message) {
        super(message);
    }

    ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    ConnectionPoolException(Throwable cause) {
        super(cause);
    }

    public ConnectionPoolException(String message,
                                   Throwable cause,
                                   boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
