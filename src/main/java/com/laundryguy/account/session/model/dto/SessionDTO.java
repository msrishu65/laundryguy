package com.laundryguy.account.session.model.dto;

import com.laundryguy.account.session.model.entity.SessionEntity;
import com.laundryguy.booking.model.apiRequest.LoginRequest;
import com.laundryguy.booking.model.enums.UserClientType;
import org.apache.commons.lang.StringUtils;

/**
 * @Author maninder
 */
public class SessionDTO {
    private String memebrId;
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
        sessionDTO.memebrId = loginRequest.getCell();
        sessionDTO.id = id;
        sessionDTO.remember = Boolean.parseBoolean(loginRequest.getRemember());
        return sessionDTO;
    }

    /* build SessionDTO from signUp Request */

    public static SessionDTO build(String memberId, long id, UserClientType clientId) {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.clientType = clientId;
        sessionDTO.memebrId = memberId;
        sessionDTO.id = id;
        return sessionDTO;
    }

    public static SessionDTO build(String loginId, UserClientType clientType) {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.clientType = clientType;
        sessionDTO.memebrId = loginId;
        return sessionDTO;
    }

    public static SessionDTO build(SessionEntity sessionEntity) {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.clientType = UserClientType.fromIdentifier(sessionEntity.getClientIdentifier());
        sessionDTO.memebrId = sessionEntity.getMemberId();
        sessionDTO.id = sessionEntity.getId();
        if (StringUtils.isNotBlank(sessionEntity.getNewTokenId()))
            sessionDTO.newToken = sessionEntity.getNewTokenId();
        return sessionDTO;
    }

    public String getMemebrId() {
        return memebrId;
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
        return "SessionDTO [memebrId=" + memebrId + ", clientType=" + clientType + "]";
    }

    public String getNewToken() {
        return newToken;
    }
}
