package com.laundryguy.booking.model.apiResponse;

import com.laundryguy.booking.model.entity.Booking;
import com.laundryguy.booking.model.entity.BookingAddress;

import java.sql.Timestamp;

/**
 * Created by maninder on 9/7/16.
 */
public class BookingResponse {

    private Integer bookingId;
    private Timestamp timeSlot;
    private Double amount;
    private Integer status;
    private BookingAddress bookingAddress;


    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Timestamp getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(Timestamp timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BookingAddress getBookingAddress() {
        return bookingAddress;
    }

    public void setBookingAddress(BookingAddress bookingAddress) {
        this.bookingAddress = bookingAddress;
    }

    public static BookingResponse build(Booking booking,BookingAddress bookingAddress) {
        BookingResponse response=new BookingResponse();
        response.setAmount(booking.getAmount());
        response.setBookingId(booking.getBookingId());
        response.setBookingAddress(bookingAddress);
        response.setStatus(booking.getStatus());
        response.setTimeSlot(booking.getTimeSlot());
        return response;
    }
}
