package com.sectorseven.service.common;

/**
 * Root runtime exception for the service layer
 */
public abstract class AbstractServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AbstractServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AbstractServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractServiceException(String message) {
        super(message);
    }

    public AbstractServiceException(Throwable cause) {
        super(cause);
    }
}
