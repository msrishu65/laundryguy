package com.laundryguy.booking.model.Exception;

import com.laundryguy.booking.model.enums.UserProfileErrorCode;

/**
 * Created by maninder on 19/7/16.
 */
public class UserProfileException extends RuntimeException {

    private UserProfileErrorCode errorCode;

    public UserProfileException(UserProfileErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public UserProfileErrorCode getErrorCode() {
        return errorCode;
    }
}
