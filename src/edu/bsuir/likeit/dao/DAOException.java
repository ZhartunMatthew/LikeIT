package edu.bsuir.likeit.dao;

/**
 * Created by Victoria on 25.01.2017.
 */
public class DAOException extends Exception{
        DAOException() {
        }

        DAOException(String message) {
            super(message);
        }

        DAOException(String message, Throwable cause) {
            super(message, cause);
        }

        DAOException(Throwable cause) {
            super(cause);
        }

        DAOException(String message,
                     Throwable cause,
                     boolean enableSuppression,
                     boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
}
