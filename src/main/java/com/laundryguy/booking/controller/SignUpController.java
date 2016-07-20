package com.laundryguy.booking.controller;

import com.laundryguy.account.session.model.dto.SessionDTO;
import com.laundryguy.account.session.service.SessionService;
import com.laundryguy.booking.model.apiRequest.SignUpRequest;
import com.laundryguy.booking.model.apiResponse.RestApiResponse;
import com.laundryguy.booking.model.config.SignUpConfig;
import com.laundryguy.booking.model.entity.Member;
import com.laundryguy.booking.model.enums.UserClientType;
import com.laundryguy.booking.service.OtpService;
import com.laundryguy.booking.service.SignUpService;
import com.laundryguy.booking.service.UserProfileService;
import com.laundryguy.booking.utils.WebUtils;
import com.laundryguy.booking.validator.SignUpValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by maninder on 20/7/16.
 */
@Controller
@RequestMapping("/account/signup")
public class SignUpController extends ExceptionController {

    public static final Logger logger = LogManager.getLogger(SignUpController.class);

    @Autowired
    private SignUpValidator validator;

    @Autowired
    private SignUpConfig signUpConfig;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private SessionService sessionService;

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    RestApiResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest, @RequestHeader("X-MClient") Integer clientId,
                           HttpServletResponse response, HttpServletRequest request) {
        logger.info("signUp request received: " + signUpRequest);
        signUpRequest.setIpAddress(WebUtils.getClientIpAddress(request));
        UserClientType client = UserClientType.fromIdentifier(clientId);
        validator.validateSignUpRequest(signUpRequest, client);
        boolean otpVerificationEnabled = signUpConfig.isOtpVerificationEnabled(client);

        logger.info("otp verification enabled: " + otpVerificationEnabled);

        if (otpVerificationEnabled) {
            //generate and send otp for verification
            otpService.sendOtp(signUpRequest.getCell());
            return RestApiResponse.buildSuccess();
        } else {
            signUpUser(signUpRequest, client, response);
            return RestApiResponse.buildSuccess();
        }
    }

    //TODO: write code for otp verification once enabled

    private void signUpUser(SignUpRequest signUpRequest, UserClientType clientId, HttpServletResponse response) {
        Member member = signUpService.signUp(signUpRequest, clientId);
        long id = signUpService.getIdForMemberId(member.getMemberId());
        //creating session
        SessionDTO sessionDTO = SessionDTO.build(member.getMemberId(), id, clientId);
        logger.info("sessionDTO:" + sessionDTO);
        sessionService.createSession(sessionDTO);
    }

}
