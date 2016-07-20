package com.laundryguy.booking.validator;

import com.laundryguy.booking.model.Exception.UserProfileException;
import com.laundryguy.booking.model.apiRequest.LoginRequest;
import com.laundryguy.booking.model.entity.Member;
import com.laundryguy.booking.model.enums.UserProfileErrorCode;
import com.laundryguy.booking.service.UserProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by maninder on 19/7/16.
 */
@Component
public class LoginValidator {

    @Autowired
    private UserProfileService userProfileService;

    private static Logger logger = LogManager.getLogger(LoginValidator.class);

    public long validateLoginRequest(LoginRequest loginRequest) {
        logger.info("validating user credentials");
        Member member = validateMember(loginRequest.getCell());
        if (!userProfileService.isValidPassword(member, loginRequest.getPassword())) {
            logger.error("invalid password");
            throw new UserProfileException(UserProfileErrorCode.E008);
        }
        return member.getId();
    }

    public Member validateMember(String cell) {
        logger.info("validating member " + cell);
        Member m = userProfileService.getMemberFromCell(cell);
        if (m == null)
            throw new UserProfileException(UserProfileErrorCode.E007);
        else if (!m.getEnabled())
            throw new UserProfileException(UserProfileErrorCode.E101);
        logger.info("member is:" + m.getMemberId());
        return m;
    }

}
