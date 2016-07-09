package com.laundryguy.model.enums;

/**
 * Created by maninder on 9/7/16.
 */
public enum BookingStatus {

    INITIATED(0),COMPLETED(1),FAILED(2);

    int status;

    public int getStatus() {
        return status;
    }

    BookingStatus(int status) {
        this.status=status;
    }
}
