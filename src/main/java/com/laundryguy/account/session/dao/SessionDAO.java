package com.laundryguy.account.session.dao;
/*
 * Author:Maninder Singh
 */

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.view.Stale;
import com.couchbase.client.java.view.ViewQuery;
import com.couchbase.client.java.view.ViewResult;
import com.couchbase.client.java.view.ViewRow;
import com.laundryguy.account.session.model.constants.SessionDaoCouchBaseConstants;
import com.laundryguy.account.session.model.constants.SessionEntityConstants;
import com.laundryguy.account.session.model.entity.SessionEntity;
import com.laundryguy.couchbase.CouchbaseManager;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class SessionDAO {

    private static final Logger logger = LogManager.getLogger(SessionDAO.class);

    @Autowired
    private CouchbaseManager couchbaseManager;

    private Bucket sessionBucket;

    @PostConstruct
    public void init() {
        sessionBucket = couchbaseManager.getSessionBucket();
    }


    private String getDocumentId(String tokenID) {
        return (SessionEntityConstants.DOCUMENT_TYPE + ":" + tokenID);
    }

    public SessionEntity getSessionEntityFromToken(String token) {
        if (StringUtils.isBlank(token))
            return null;
        JsonDocument jsonDocument = sessionBucket.get(getDocumentId(token));
        SessionEntity sessionEntity = (jsonDocument == null) ? null : SessionEntity.buildFromMap(jsonDocument.content().toMap());
        return sessionEntity;
    }

    public void createSession(SessionEntity sessionEntity) {
        logger.info("creating session: " + sessionEntity);
        JsonObject content = JsonObject.from(sessionEntity.toMap());
        JsonDocument document = JsonDocument.create(getDocumentId(sessionEntity.getTokenId()), content);
        sessionBucket.insert(document);
        logger.info("session created");
    }

    public void updateSession(SessionEntity sessionEntity) {
        logger.info("updating session: " + sessionEntity);
        JsonObject content = JsonObject.from(sessionEntity.toMap());
        JsonDocument document = JsonDocument.create(getDocumentId(sessionEntity.getTokenId()), content);
        sessionBucket.replace(document);
        logger.info("session updated");
    }

    public void removeSession(SessionEntity sessionEntity) {
        sessionBucket.remove(SessionEntityConstants.DOCUMENT_TYPE + ":" + sessionEntity.getTokenId());
    }

/*
    public List<SessionEntity> getSessionEntitiesFromEmail(String email) {
        List<String> tokens = new ArrayList<String>();
        List<SessionEntity> sessionEntityList = new ArrayList<SessionEntity>();
        ViewQuery query = ViewQuery.from(SessionDaoCouchBaseConstants.DesignDoc.UserSession, SessionDaoCouchBaseConstants.Views.GetAllSessionsForMemberId).key(email).stale(Stale.FALSE);
        ViewResult result = sessionBucket.query(query);

        for (ViewRow row : result) {
            tokens.add(row.value().toString());
        }
        if (CollectionUtils.isNotEmpty(tokens)) {
            for (String token : tokens) {
                sessionEntityList.add(getSessionEntityFromToken(token));
            }
        }
        return sessionEntityList;
    }
*/
}

