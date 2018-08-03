package com.manager.common.exception;

/**
 * business exception
 *
 * @author chao
 * @since 2018-08-01
 */
public class BusinessException extends RuntimeException {
    
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
