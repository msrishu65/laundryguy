package com.laundryguy.controller;

import com.laundryguy.model.apiRequest.BookingRequest;
import com.laundryguy.model.apiResponse.BookingResponse;
import com.laundryguy.model.apiResponse.RestApiResponse;
import com.laundryguy.model.entity.Booking;
import com.laundryguy.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by maninder on 9/7/16.
 */
@Controller
@RequestMapping("/book")
public class BookingController extends ExceptionController {

   public static final  String email="maninder@gmail.com";

    @Autowired
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    RestApiResponse book(@Valid @RequestBody BookingRequest bookingRequest) {
        System.out.println("booking for " + bookingRequest);
        bookingService.book(email, bookingRequest);
        return RestApiResponse.buildSuccess();
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    RestApiResponse getBookingHistory() {
        List<BookingResponse> bookingResponseList=bookingService.getHistory(email);
        return RestApiResponse.buildSuccess(bookingResponseList);
    }



    @RequestMapping(value = "ping",method = RequestMethod.GET)
    public
    @ResponseBody
    String ping() {
        return "Hello World!";
    }


}
