package com.laundryguy.account.session.model.entity;

import com.laundryguy.account.session.model.constants.SessionEntityConstants;
import com.laundryguy.account.session.model.dto.SessionDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.collections.MapUtils.*;

public class SessionEntity {
    private static final Logger logger = LogManager.getLogger(SessionEntity.class);

    private String type = SessionEntityConstants.DOCUMENT_TYPE;
    private long id;
    private String memberId;
    private String tokenId;
    private String newTokenId;
    private String clientType;
    private Timestamp expiryTimeStamp;
    private Timestamp lapseTimeStamp;
    private int clientIdentifier;
    private Timestamp createdTimeStamp;
    private boolean isEnabled;

    public static SessionEntity build(SessionDTO sessionDTO) {
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.memberId = sessionDTO.getMemebrId();
        sessionEntity.clientType = sessionDTO.getClientType().getValue();
        sessionEntity.clientIdentifier = sessionDTO.getClientType().getIdentifier();
        sessionEntity.tokenId = sessionDTO.getToken();
        sessionEntity.id = sessionDTO.getId();
        return sessionEntity;
    }

    public static SessionEntity buildFromMap(Map<String, Object> dataMap) {
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.id = getLongValue(dataMap, SessionEntityConstants.ID);
        sessionEntity.memberId = getString(dataMap, SessionEntityConstants.MEMBER_ID);
        sessionEntity.tokenId = getString(dataMap, SessionEntityConstants.TOKEN_ID);
        sessionEntity.newTokenId = getString(dataMap, SessionEntityConstants.NEW_TOKEN_ID);
        sessionEntity.clientType = getString(dataMap, SessionEntityConstants.CLIENT_TYPE);
        sessionEntity.clientIdentifier = getIntValue(dataMap, SessionEntityConstants.CLIENT_IDENTIFIER);
        sessionEntity.expiryTimeStamp = new Timestamp((Long) getObject(dataMap, SessionEntityConstants.EXPIRY_TIME));
        sessionEntity.lapseTimeStamp = new Timestamp((Long) getObject(dataMap, SessionEntityConstants.LAPSE_TIME));
        sessionEntity.createdTimeStamp = new Timestamp((Long) getObject(dataMap, SessionEntityConstants.CREATED_TIME));
        sessionEntity.isEnabled = getBooleanValue(dataMap, SessionEntityConstants.IS_ENABLED);
        return sessionEntity;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(SessionEntityConstants._TYPE, this.type);
        data.put(SessionEntityConstants.ID, this.getId());
        data.put(SessionEntityConstants.MEMBER_ID, this.memberId);
        data.put(SessionEntityConstants.TOKEN_ID, this.tokenId);
        data.put(SessionEntityConstants.NEW_TOKEN_ID, this.newTokenId);
        data.put(SessionEntityConstants.CLIENT_TYPE, this.clientType);
        data.put(SessionEntityConstants.CLIENT_IDENTIFIER, this.clientIdentifier);
        data.put(SessionEntityConstants.EXPIRY_TIME, this.expiryTimeStamp.getTime());
        data.put(SessionEntityConstants.LAPSE_TIME, this.lapseTimeStamp.getTime());
        data.put(SessionEntityConstants.CREATED_TIME, this.createdTimeStamp.getTime());
        data.put(SessionEntityConstants.IS_ENABLED, this.isEnabled);
        return data;
    }

    /* ALL GETTERS */
    public String getMemberId() {
        return memberId;
    }

    public int getClientIdentifier() {
        return clientIdentifier;
    }

    //TODO session expiry rules
    // createdDate, lastLogin, loginHistory


    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Timestamp getExpiryTimeStamp() {
        return expiryTimeStamp;
    }

    public void setExpiryTimeStamp(Timestamp expiryTimeStamp) {
        this.expiryTimeStamp = expiryTimeStamp;
    }

    public long getId() {
        return id;
    }

    public void setLapseTimeStamp(Timestamp elapseTimeStamp) {
        this.lapseTimeStamp = elapseTimeStamp;
    }

    public Timestamp getLapseTimeStamp() {
        return lapseTimeStamp;
    }

    public String getNewTokenId() {
        return newTokenId;
    }

    public void setNewTokenId(String newTokenId) {
        this.newTokenId = newTokenId;
    }

    public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }


    public boolean isEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public String toString() {
        return "SessionEntity{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", memberId='" + memberId + '\'' +
                ", clientType='" + clientType + '\'' +
                ", expiryTimeStamp=" + expiryTimeStamp +
                ", lapseTimeStamp=" + lapseTimeStamp +
                ", clientIdentifier=" + clientIdentifier +
                ", createdTimeStamp=" + createdTimeStamp +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
