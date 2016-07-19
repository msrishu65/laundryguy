package com.laundryguy.booking.controller;

import com.laundryguy.booking.model.apiRequest.LoginRequest;
import com.laundryguy.booking.model.apiResponse.RestApiResponse;
import com.laundryguy.booking.model.constants.ParamConstants;
import com.laundryguy.booking.model.enums.UserClientType;
import com.laundryguy.booking.service.LoginService;
import com.laundryguy.booking.utils.WebUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/*
 * Author: Maninder Singh
 */

@Controller
@RequestMapping("/account/login")
public class LoginController extends ExceptionController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);


    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    RestApiResponse login(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest servletRequest, HttpServletResponse response, @RequestHeader(ParamConstants.X_M_CLIENT) int client) {
        logger.info("Login request received: " + loginRequest);
        loginRequest.setClientType(UserClientType.fromIdentifier(client));
        loginRequest.setIpAddress(WebUtils.getClientIpAddress(servletRequest));
        loginService.loginUser(loginRequest,response);
        return RestApiResponse.buildSuccess();
    }

}
