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
        createSession(loginRequest, response, id);
    }

    private void recordLoginHistory(LoginRequest loginRequest) {
     //TODO: saving login history
    }


    private void createSession(LoginRequest loginRequest, HttpServletResponse response, long id) {
        // token generation and creating session
        SessionDTO sessionDTO = SessionDTO.build(loginRequest, id);
        logger.info("sessionDTO:" + sessionDTO);
        String sessionToken = sessionService.createSession(sessionDTO);

        // getting details ready for response
        response.setHeader("token", sessionToken);
        response.setHeader("hashId", sessionService.getHashOfIdentifier(Long.toString(id)));
    }

}
