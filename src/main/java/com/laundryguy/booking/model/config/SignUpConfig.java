package com.laundryguy.booking.model.config;

import com.laundryguy.booking.model.enums.UserClientType;
import org.springframework.stereotype.Component;

/**
 * Created by maninder on 20/7/16.
 */
@Component
public class SignUpConfig {
    public boolean isOtpVerificationEnabled(UserClientType client) {
        return false;
    }
}
