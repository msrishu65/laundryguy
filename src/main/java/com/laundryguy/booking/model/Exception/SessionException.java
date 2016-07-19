package com.laundryguy.booking.model.Exception;

import com.laundryguy.booking.model.enums.SessionErrorCode;

/**
 * Created by maninder on 19/7/16.
 */
public class SessionException extends RuntimeException{

    private SessionErrorCode errorCode;

    public SessionException(SessionErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public SessionErrorCode getErrorCode() {
        return errorCode;
    }
}
