package com.laundryguy.account.session.model.dto;

import com.laundryguy.account.session.model.entity.SessionEntity;
import com.laundryguy.booking.model.apiRequest.LoginRequest;
import com.laundryguy.booking.model.enums.UserClientType;
import org.apache.commons.lang.StringUtils;

/**
 * @Author Saarthak Vats (09-Jul-2015)
 */
public class SessionDTO {
    private String memberEmail;
    private UserClientType clientType;
    private String token;
    private boolean remember;
    private String newToken;

    public long getId() {
        return id;
    }

    private long id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /* build SessionDTO from login Request */
    public static SessionDTO build(LoginRequest loginRequest, long id) {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.clientType = loginRequest.getClientType();
        sessionDTO.memberEmail = loginRequest.getLoginId();
        sessionDTO.id = id;
        sessionDTO.remember = Boolean.parseBoolean(loginRequest.getRemember());
        return sessionDTO;
    }

    /* build SessionDTO from signUp Request */
/*
    public static SessionDTO build(SignUpRequest signUpRequest, long id, int clientId) {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.clientType = UserClientType.fromIdentifier(clientId);
        sessionDTO.memberEmail = signUpRequest.getEmail();
        sessionDTO.id = id;
        return sessionDTO;
    }

*/

    public static SessionDTO build(String loginId, UserClientType clientType) {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.clientType = clientType;
        sessionDTO.memberEmail = loginId;
        return sessionDTO;
    }

    public static SessionDTO build(SessionEntity sessionEntity) {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.clientType = UserClientType.fromIdentifier(sessionEntity.getClientIdentifier());
        sessionDTO.memberEmail = sessionEntity.getMemberId();
        sessionDTO.id = sessionEntity.getId();
        if (StringUtils.isNotBlank(sessionEntity.getNewTokenId()))
            sessionDTO.newToken = sessionEntity.getNewTokenId();
        return sessionDTO;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public UserClientType getClientType() {
        return clientType;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    @Override
    public String toString() {
        return "SessionDTO [memberEmail=" + memberEmail + ", clientType=" + clientType + "]";
    }

    public String getNewToken() {
        return newToken;
    }
}
