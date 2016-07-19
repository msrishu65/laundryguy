package com.laundryguy.booking.model.apiRequest;

/**
 * Created by maninder on 9/7/16.
 */
public class BookingRequest {

    private String addressId;
    private BookingAddressDetails address;
    private Long time;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public BookingAddressDetails getAddress() {
        return address;
    }

    public void setAddress(BookingAddressDetails address) {
        this.address = address;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
