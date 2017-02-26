package edu.bsuir.likeit.connectionpool;

/**
 * Created by Victoria on 25.01.2017.
 */
public class ConnectionPoolInitException  extends RuntimeException {
    private static final long serialVersionUID = -4361500870819953644L;

    ConnectionPoolInitException() {
    }

    ConnectionPoolInitException(String message) {
        super(message);
    }

    ConnectionPoolInitException(String message, Throwable cause) {
        super(message, cause);
    }

    ConnectionPoolInitException(Throwable cause) {
        super(cause);
    }

    ConnectionPoolInitException(String message,
                                Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
