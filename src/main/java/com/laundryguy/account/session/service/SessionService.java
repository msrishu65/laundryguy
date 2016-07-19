package com.laundryguy.account.session.service;

/*
 * Author : Maninder Singh
 */

import com.laundryguy.account.session.config.SessionConfig;
import com.laundryguy.account.session.dao.SessionDAO;
import com.laundryguy.account.session.model.dto.SessionDTO;
import com.laundryguy.account.session.model.entity.SessionEntity;
import com.laundryguy.account.token.service.TokenService;
import com.laundryguy.booking.model.Exception.SessionException;
import com.laundryguy.booking.model.enums.SessionErrorCode;
import com.laundryguy.booking.model.enums.UserClientType;
import com.laundryguy.booking.utils.HashingUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class SessionService {

    private static final Logger logger = LogManager.getLogger(SessionService.class);
    @Autowired
    SessionDAO sessionDao;
    @Autowired
    TokenService tokenService;
    @Autowired
    private SessionConfig sessionConfig;

    /**
     * Authenticates the token and returns SessionDTO
     * mainly used in SessionInterceptor to check if user is logged in or not
     */
    public SessionDTO authenticateTokenAndGetData(String token, UserClientType clientType) {
//        logger.info("authenticating user");
        if (StringUtils.isBlank(token)) {
//            logger.info("token is empty");
            throw new SessionException(SessionErrorCode.T001);
        }

        String[] tokenParts = null;
        String tokenId = null;
        String hashId = null;
        try {
            tokenParts = token.split("\\.", 2);
            hashId = tokenParts[0];
            tokenId = tokenParts[1];
        } catch (Exception e) {
            throw new SessionException(SessionErrorCode.T001);
        }

        SessionEntity sessionEntity = validateSessionTokenAndReturnSessionEntity(tokenId);
//        logger.info("token matched.checking hashId");
        if (!getHashOfIdentifier(Long.toString(sessionEntity.getId())).equals(hashId))
            throw new SessionException(SessionErrorCode.T001);

        if (sessionEntity.getClientIdentifier() != clientType.getIdentifier()) {
            logger.error("Token is not valid for all user client type");
            throw new SessionException(SessionErrorCode.T001);
        }

        return SessionDTO.build(sessionEntity);
    }

    public String createSession(SessionDTO sessionDTO) {
        sessionDTO.setToken(tokenService.generateRandomToken());
        SessionEntity sessionEntity = SessionEntity.build(sessionDTO);
        sessionEntity = setTimeStampsAndReturnSessionEntity(sessionEntity, sessionDTO.getClientType(), sessionDTO.isRemember());
        sessionEntity.setIsEnabled(true);
        logger.info("sessionEntity is " + sessionEntity);
        sessionDao.createSession(sessionEntity);
        return sessionEntity.getTokenId();
    }

    private SessionEntity setTimeStampsAndReturnSessionEntity(SessionEntity sessionEntity, UserClientType clientType, boolean rememberMe) {
        if (rememberMe) {
            logger.info("remember me boolean is enabled, so setting expiry time according to client type ");
            sessionEntity.setExpiryTimeStamp(getExpiryTimeReMeByClientType(clientType));
            sessionEntity.setLapseTimeStamp(getLapseTimeReMeByClientType(clientType));
        } else {
            logger.info("remember me boolean is Disabled , so setting default expiry time ");
            sessionEntity.setExpiryTimeStamp(getDefaultExpiryTimeByClientType(clientType));
            sessionEntity.setLapseTimeStamp(getDefaultLapseTimeByClientType(clientType));
        }
        sessionEntity.setCreatedTimeStamp(new Timestamp(new Date().getTime()));
        return sessionEntity;
    }

    public SessionEntity validateSessionTokenAndReturnSessionEntity(String token) {
        SessionEntity sessionEntity = sessionDao.getSessionEntityFromToken(token);
        logger.info("session entity is " + sessionEntity);
        if (sessionEntity != null) {

            Timestamp expiryTimeStamp = sessionEntity.getExpiryTimeStamp();
            Timestamp lapseTimeStamp = sessionEntity.getLapseTimeStamp();
            Timestamp createdTimeStamp = sessionEntity.getCreatedTimeStamp();
            Timestamp currTimeStamp = new Timestamp(new Date().getTime());

            float lapseTime = (float) (lapseTimeStamp.getTime());
            float createdTime = (float) (createdTimeStamp.getTime());
            float refreshTokenOpeningTime = ((float) sessionConfig.getRefreshTokenWindowOpeningTime() / (float) 100);
            float refreshTokenTime = (lapseTime - createdTime) * refreshTokenOpeningTime + createdTime;
            Timestamp refreshTokenTimeStamp = new Timestamp((long) refreshTokenTime);

            //if current time stamp is after expiry time stamp ,token has expired.don't authenticate this call
            if (currTimeStamp.after(expiryTimeStamp)) {
                logger.info("token expired");
                throw new SessionException(SessionErrorCode.T002);
            }

            //if current timestamp is after lapse time stamp session should not have newtoken i.e it's main token should be refreshed one
            if (currTimeStamp.after(lapseTimeStamp)) {
                if (StringUtils.isNotBlank(sessionEntity.getNewTokenId())) {
                    logger.info("new token needed after lapse time");
                    throw new SessionException(SessionErrorCode.T002);
                } else {
                    SessionEntity newSessionEntity = buildNewSessionEntity(sessionEntity, tokenService.generateRandomToken());
                    newSessionEntity.setNewTokenId(newSessionEntity.getTokenId());

                    //set generated token as new token for linking
                    sessionEntity.setNewTokenId(newSessionEntity.getNewTokenId());
                    sessionDao.updateSession(sessionEntity);

                    return newSessionEntity;
                }
            }

            //if current time stamp is after refresh token time stamp ,need to refresh the token
            if (currTimeStamp.after(refreshTokenTimeStamp)) {
//                logger.info("refreshing token");
                //new token generated
                String newToken = tokenService.generateRandomToken();
                buildNewSessionEntity(sessionEntity, newToken);
                //set generated token as new token for linking
                sessionEntity.setNewTokenId(newToken);
                sessionDao.updateSession(sessionEntity);
            }
//            logger.info("token is not expired");
            return sessionEntity;
        }
        throw new SessionException(SessionErrorCode.T001);
    }

    public SessionEntity buildNewSessionEntity(SessionEntity sessionEntity, String newToken) {
        //build sessionDTO
        SessionDTO sessionDTO = SessionDTO.build(sessionEntity);
        //set generated token as token
        sessionDTO.setToken(newToken);
        //build new entity
        SessionEntity newSessionEntity = SessionEntity.build(sessionDTO);
        newSessionEntity = setTimeStampsAndReturnSessionEntity(newSessionEntity, sessionDTO.getClientType(), sessionDTO.isRemember());
        logger.info("sessionEntity is " + newSessionEntity);
        newSessionEntity.setIsEnabled(true);
        sessionDao.createSession(newSessionEntity);
        return newSessionEntity;
    }

    /**
     * returns default expiry time for user session as per client type
     */
    private Timestamp getDefaultExpiryTimeByClientType(UserClientType clientType) {
        Timestamp expiryTimeStamp = new Timestamp(new Date().getTime());
        DateTime expiryDateTime;
        switch (clientType) {
            case DesktopWebsite:
                expiryDateTime = DateTime.now().plusHours(sessionConfig.getDesktopDefaultExpiryHours());
                break;
/*
            case MobileWebsite:
                expiryDateTime = DateTime.now().plusHours(sessionConfig.getMobileWebsiteDefaultExpiryHours());
                break;
            case Android:
                expiryDateTime = DateTime.now().plusDays(sessionConfig.getAndroidExpiryDays());
                break;
            case Blackberry:
                expiryDateTime = DateTime.now().plusDays(sessionConfig.getBlackberryExpiryDays());
                break;
            case WindowsApp:
                expiryDateTime = DateTime.now().plusDays(sessionConfig.getWindowsExpiryDays());
                break;
            case IPhone:
                expiryDateTime = DateTime.now().plusDays(sessionConfig.getIPhoneExpiryDays());
                break;
            case IPad:
                expiryDateTime = DateTime.now().plusDays(sessionConfig.getIPadExpiryDays());
                break;
*/
            default:
                logger.info("Setting expiry time for Unknown client :" + clientType.getValue() + " with expiry time :" + expiryTimeStamp);
                return expiryTimeStamp;
        }
        expiryTimeStamp = new Timestamp(expiryDateTime.toDate().getTime());
        logger.info("Setting expiry time for client :" + clientType.getValue() + " with expiry time :" + expiryTimeStamp);
        return expiryTimeStamp;
    }


    /**
     * returns the expiry time for user session as per client type
     */
    private Timestamp getExpiryTimeReMeByClientType(UserClientType clientType) {

        Timestamp expiryTimeStamp = new Timestamp(new Date().getTime());
        DateTime expiryDateTime;
        switch (clientType) {
            case DesktopWebsite:
                expiryDateTime = DateTime.now().plusHours(sessionConfig.getDesktopRemeberMeExpiryHours());
                break;
/*
            case MobileWebsite:
                expiryDateTime = DateTime.now().plusHours(sessionConfig.getMobileWebsiteRemeberMeExpiryHours());
                break;
            case Android:
                expiryDateTime = DateTime.now().plusDays(sessionConfig.getAndroidReMeExpiryDays());
                break;
            case Blackberry:
                expiryDateTime = DateTime.now().plusDays(sessionConfig.getBlackberryReMeExpiryDays());
                break;
            case WindowsApp:
                expiryDateTime = DateTime.now().plusDays(sessionConfig.getWindowsReMeExpiryDays());
                break;
            case IPhone:
                expiryDateTime = DateTime.now().plusDays(sessionConfig.getiPhoneReMeExpiryDays());
                break;
            case IPad:
                expiryDateTime = DateTime.now().plusDays(sessionConfig.getiPadReMeExpiryDays());
                break;
*/
            default:
                logger.info("Setting expiry time for Unknown client :" + clientType.getValue() + " with lapse time :" + expiryTimeStamp);
                return expiryTimeStamp;

        }
        expiryTimeStamp = new Timestamp(expiryDateTime.toDate().getTime());
        logger.info("Setting expiry time for client :" + clientType.getValue() + " with expiry time :" + expiryTimeStamp);
        return expiryTimeStamp;
    }

    private Timestamp getDefaultLapseTimeByClientType(UserClientType clientType) {
        Timestamp lapseTimeStamp = new Timestamp(new Date().getTime());
        DateTime lapseDateTime;
        switch (clientType) {
            case DesktopWebsite:
                lapseDateTime = DateTime.now().plusHours(sessionConfig.getDesktopDefaultLapseHours());
                break;
/*
            case MobileWebsite:
                lapseDateTime = DateTime.now().plusHours(sessionConfig.getMobileWebsiteLapseHours());
                break;
            case Android:
                lapseDateTime = DateTime.now().plusDays(sessionConfig.getAndroidLapseDays());
                break;
            case Blackberry:
                lapseDateTime = DateTime.now().plusDays(sessionConfig.getBlackberryLapseDays());
                break;
            case WindowsApp:
                lapseDateTime = DateTime.now().plusDays(sessionConfig.getWindowsLapseDays());
                break;
            case IPhone:
                lapseDateTime = DateTime.now().plusDays(sessionConfig.getIPhoneLapseDays());
                break;
            case IPad:
                lapseDateTime = DateTime.now().plusDays(sessionConfig.getIPadLapseDays());
                break;
*/
            default:
                logger.info("Setting lapse time for Unknown client :" + clientType.getValue() + " with lapse time :" + lapseTimeStamp);
                return lapseTimeStamp;
        }
        lapseTimeStamp = new Timestamp(lapseDateTime.toDate().getTime());
        logger.info("Setting lapse time for client :" + clientType.getValue() + " with lapse time :" + lapseTimeStamp);
        return lapseTimeStamp;
    }

    private Timestamp getLapseTimeReMeByClientType(UserClientType clientType) {
        Timestamp lapseTimeStamp = new Timestamp(new Date().getTime());
        DateTime lapseDateTime;
        switch (clientType) {
            case DesktopWebsite:
                lapseDateTime = DateTime.now().plusHours(sessionConfig.getDesktopRemeberMeLapseHours());
                break;
/*
            case MobileWebsite:
                lapseDateTime = DateTime.now().plusHours(sessionConfig.getMobileWebsiteRemeberMeLapseHours());
                break;
            case Android:
                lapseDateTime = DateTime.now().plusDays(sessionConfig.getAndroidRemeberMeLapseDays());
                break;
            case Blackberry:
                lapseDateTime = DateTime.now().plusDays(sessionConfig.getBlackberryRemeberMeLapseDays());
                break;
            case WindowsApp:
                lapseDateTime = DateTime.now().plusDays(sessionConfig.getWindowsRemeberMeLapseDays());
                break;
            case IPhone:
                lapseDateTime = DateTime.now().plusDays(sessionConfig.getiPhoneRemeberMeLapseDays());
                break;
            case IPad:
                lapseDateTime = DateTime.now().plusDays(sessionConfig.getiPadRemeberMeLapseDays());
                break;
*/
            default:
                logger.info("Setting lapse time for Unknown client :" + clientType.getValue() + " with lapse time :" + lapseTimeStamp);
                return lapseTimeStamp;
        }
        lapseTimeStamp = new Timestamp(lapseDateTime.toDate().getTime());
        logger.info("Setting lapse time for client :" + clientType.getValue() + " with lapse time :" + lapseTimeStamp);
        return lapseTimeStamp;
    }

/*
    public void removeSessionsForEmail(String email) {
        List<SessionEntity> sessionEntityList = sessionDao.getSessionEntitiesFromEmail(email);
        logger.info("removing user sessions, count is" + sessionEntityList.size());
        if (CollectionUtils.isEmpty(sessionEntityList))
            return;
        for (SessionEntity sessionEntity : sessionEntityList) {
            removeUserSession(sessionEntity);
        }
    }
*/


/*
    public boolean removeSessionsForEmail(String email, UserClientType userClientType) {
        List<SessionEntity> sessionEntityList = sessionDao.getSessionEntitiesFromEmail(email);

        if (CollectionUtils.isEmpty(sessionEntityList))
            return false;
        for (SessionEntity sessionEntity : sessionEntityList) {
            if (userClientType.equals(UserClientType.fromIdentifier(sessionEntity.getClientIdentifier()))) {
                logger.info("removed user session : " + sessionEntity.getMemberId());
                removeUserSession(sessionEntity);
            }
        }
        return false;
    }
*/


    public void removeUserSession(SessionEntity sessionEntity) {
        logger.info("removing user session with token" + sessionEntity.getMemberId());
        sessionDao.removeSession(sessionEntity);
        logger.info("clearing all sessions for" + sessionEntity.getMemberId());
    }

    public String getHashOfIdentifier(String s) {
        String id[] = new String[1];
        id[0] = s;
        return HashingUtil.getMD5Hash(id);
    }

}
