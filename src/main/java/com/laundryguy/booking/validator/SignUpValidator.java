package com.laundryguy.booking.validator;

import com.laundryguy.account.token.service.TokenService;
import com.laundryguy.booking.model.Exception.UserProfileException;
import com.laundryguy.booking.model.apiRequest.SignUpRequest;
import com.laundryguy.booking.model.entity.Member;
import com.laundryguy.booking.model.enums.UserClientType;
import com.laundryguy.booking.model.enums.UserProfileErrorCode;
import com.laundryguy.booking.service.UserProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by maninder on 20/7/16.
 */
@Component
public class SignUpValidator {

    private static Logger logger = LogManager.getLogger(SignUpValidator.class);

    private static final List<String> allowedCellStartingDigit = Arrays.asList("7", "8", "9");
    private static final short CONTACT_NO_LENGTH = 10;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private TokenService tokenService;

    public void validateSignUpRequest(SignUpRequest signUpRequest, UserClientType client) {
        doDataSanity(signUpRequest, client);
        doSystemSanity(signUpRequest);

    }

    private void doSystemSanity(SignUpRequest signUpRequest) {
        validateContactNo(signUpRequest.getCell());
        generateMemberId(signUpRequest);
    }

    private void generateMemberId(SignUpRequest signUpRequest) {
        int count = 0;
        while (true) {
            if (count > 3) {
                throw new RuntimeException("No unique member_id could be generated");
            }
            count++;
            String memberId = tokenService.generateRandomToken();
            Member member = userProfileService.getMember(memberId);
            if (member == null) {
                signUpRequest.setMemberId(memberId);
                break;
            }
        }
    }

    private void doDataSanity(SignUpRequest signUpRequest, UserClientType client) {
        validateContactNoFormat(signUpRequest.getCell());
        validateClient(client);
    }

    private void validateClient(UserClientType client) {
        if (client == UserClientType.Invalid) {
            throw new UserProfileException(UserProfileErrorCode.E107);
        }
    }

    public void validateContactNoFormat(String contactNo) {

        logger.info("contact no format validation");

        // format validation
        logger.info("Contact no:" + contactNo);
        try {
            Long.parseLong(contactNo);
        } catch (Exception e) {
            logger.error("invalid cell number format");
            throw new UserProfileException(UserProfileErrorCode.E005);
        }

        if (contactNo.length() != CONTACT_NO_LENGTH) {
            logger.error("invalid cell number length");
            throw new UserProfileException(UserProfileErrorCode.E006);
        }

        String firstDigit = String.valueOf(contactNo.charAt(0));
        if (!allowedCellStartingDigit.contains(firstDigit)) {
            logger.error("invalid cell number");
            throw new UserProfileException(UserProfileErrorCode.E004);
        }
    }

    public void validateContactNo(String contactNo) {
        logger.info("validating contact no " + contactNo);
        Member member = userProfileService.getMemberFromCell(contactNo);
    }

}
