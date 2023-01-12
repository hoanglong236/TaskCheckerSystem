package org.swing.app.dao.exception;


/**
 * TODO: comment this
 */
public class DaoException extends Exception {

    public DaoException(String errorMessage) {
        super(errorMessage);
    }

    public DaoException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public DaoException(Throwable throwable) {
        super(throwable);
    }
}
