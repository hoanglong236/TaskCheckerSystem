package org.swing.app.business.exception;

public class BusinessException extends Exception {

    public BusinessException(String errorMessage) {
        super(errorMessage);
    }

    public BusinessException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }
}
