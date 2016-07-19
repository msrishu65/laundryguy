package com.laundryguy.booking.service;

import com.laundryguy.booking.dao.BookingDAO;
import com.laundryguy.booking.model.apiRequest.BookingRequest;
import com.laundryguy.booking.model.apiResponse.BookingResponse;
import com.laundryguy.booking.model.entity.Booking;
import com.laundryguy.booking.model.entity.BookingAddress;
import com.laundryguy.booking.utils.CommonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maninder on 9/7/16.
 */
@Service
public class BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    @Transactional
    public void book(String email, BookingRequest bookingRequest) {
        String cell = "9999047954";
        Booking booking = Booking.build(email, cell, bookingRequest);
        String addressId = bookingRequest.getAddressId();
        if (StringUtils.isBlank(bookingRequest.getAddressId())) {
            addressId = CommonUtils.generateOrderID();
            BookingAddress address = BookingAddress.build(addressId, bookingRequest.getAddress());
            bookingDAO.saveAddress(address);
        }
        booking.setAddressId(addressId);
        bookingDAO.save(booking);
    }

    @Transactional
    public List<BookingResponse> getHistory(String email) {
        List<Booking> bookingList = bookingDAO.getBookings(email);
        List<BookingResponse> bookingResponseList = new ArrayList<BookingResponse>();
        if (CollectionUtils.isEmpty(bookingList)) {
            return bookingResponseList;
        }
        for (Booking booking : bookingList) {
            BookingAddress bookingAddress = bookingDAO.getBookingAddress(booking.getAddressId());
            bookingResponseList.add(BookingResponse.build(booking, bookingAddress));
        }
        return bookingResponseList;
    }
}
