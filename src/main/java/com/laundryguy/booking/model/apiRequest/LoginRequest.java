package com.laundryguy.booking.model.apiRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laundryguy.booking.model.enums.UserClientType;
import org.hibernate.validator.constraints.NotBlank;

public class LoginRequest {

    @JsonProperty("email")
    @NotBlank
    private String loginId;

    @JsonProperty("pwd")
    @NotBlank
    private String passcode;

    private String deviceId;

    @JsonProperty("av")
    private String appVersion;


    private String otp;

    @NotBlank
    private String remember;

    //not received from client
    private UserClientType clientType;

    private String ipAddress;

    public UserClientType getClientType() {
        return clientType;
    }

    public void setClientType(UserClientType clientType) {
        this.clientType = clientType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }
}
