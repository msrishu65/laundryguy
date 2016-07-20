package com.laundryguy.booking.service;

import com.laundryguy.account.session.model.dto.SessionDTO;
import com.laundryguy.account.session.service.SessionService;
import com.laundryguy.booking.model.apiRequest.LoginRequest;
import com.laundryguy.booking.model.apiResponse.RestApiResponse;
import com.laundryguy.booking.validator.LoginValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by maninder on 19/7/16.
 */
@Service
public class LoginService {

    private static Logger logger = LogManager.getLogger(LoginService.class);

    @Autowired
    private LoginValidator validator;

    @Autowired
    private SessionService sessionService;

    public void loginUser(LoginRequest loginRequest, HttpServletResponse response) {
        long id = validator.validateLoginRequest(loginRequest);
        recordLoginHistory(loginRequest);
        SessionDTO sessionDTO = SessionDTO.build(loginRequest, id);
        sessionService.createSession(sessionDTO);
    }

    private void recordLoginHistory(LoginRequest loginRequest) {
        //TODO: saving login history
    }


}
