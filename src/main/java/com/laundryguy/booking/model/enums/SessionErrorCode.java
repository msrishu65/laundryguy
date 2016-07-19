package com.laundryguy.booking.model.enums;

/**
 * Created by maninder on 19/7/16.
 */
public enum SessionErrorCode {
    T001("Invalid token"),
    T002("Token has expired");

    private String errorDesc;

    SessionErrorCode(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getErrorDesc() {
        return errorDesc;
    }
}
