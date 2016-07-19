package com.laundryguy.booking.model.entity;

import com.laundryguy.booking.model.apiRequest.BookingRequest;
import com.laundryguy.booking.model.enums.BookingStatus;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by maninder on 9/7/16.
 */
@Entity
@Table(name = "booking")
public class Booking {
    private Integer bookingId;
    private String userName;
    private String email;
    private String cell;
    private String addressId;
    private Timestamp timeSlot;
    private Double amount;
    private Integer status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false, insertable = true, updatable = true)
    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @Basic
    @Column(name = "user_name", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "cell", nullable = true, insertable = true, updatable = true, length = 11)
    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    @Basic
    @Column(name = "addressId", nullable = true, insertable = true, updatable = true, length = 100)
    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "time_slot", nullable = false, insertable = true, updatable = true)
    public Timestamp getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(Timestamp timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Basic
    @Column(name = "amount", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "status", nullable = true, insertable = true, updatable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (bookingId != null ? !bookingId.equals(booking.bookingId) : booking.bookingId != null) return false;
        if (userName != null ? !userName.equals(booking.userName) : booking.userName != null) return false;
        if (email != null ? !email.equals(booking.email) : booking.email != null) return false;
        if (cell != null ? !cell.equals(booking.cell) : booking.cell != null) return false;
        if (addressId != null ? !addressId.equals(booking.addressId) : booking.addressId != null) return false;
        if (timeSlot != null ? !timeSlot.equals(booking.timeSlot) : booking.timeSlot != null) return false;
        if (amount != null ? !amount.equals(booking.amount) : booking.amount != null) return false;
        if (status != null ? !status.equals(booking.status) : booking.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookingId != null ? bookingId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (cell != null ? cell.hashCode() : 0);
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        result = 31 * result + (timeSlot != null ? timeSlot.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    public static Booking build(String email, String cell, BookingRequest bookingRequest) {
        Booking booking = new Booking();
        booking.setEmail(email);
        booking.setCell(cell);
        booking.setUserName(email);
        booking.setStatus(BookingStatus.INITIATED.getStatus());
        booking.setTimeSlot(new Timestamp(bookingRequest.getTime()));
        return booking;
    }
}
