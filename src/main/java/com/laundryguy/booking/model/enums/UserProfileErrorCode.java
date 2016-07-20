package com.laundryguy.booking.model.enums;

/**
 * Created by maninder on 19/7/16.
 */
public enum UserProfileErrorCode {
    E004("Invalid CellNumber"),
    E005("Invalid CellNumber Format"),
    E006("Invalid CellNumber Length"),
    E115("Invalid Operator Id"),
    E007("Invalid login ID"),
    E008("Invalid password"),
    E009("Invalid token ID"),
    E101("User is disabled"),
    E107("Invalid client ID");

    private String errorDesc;

    UserProfileErrorCode(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getErrorDesc() {
        return errorDesc;
    }
}
