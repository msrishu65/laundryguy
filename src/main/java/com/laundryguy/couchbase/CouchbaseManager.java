package com.laundryguy.couchbase;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;

/**
 * Created by maninder
 */
@Component
public class CouchbaseManager {

    private static final Logger logger = LogManager.getLogger(CouchbaseManager.class);

//    @Value("${couchbase.hosts:127.0.0.1}")
    private String hosts="127.0.0.1";

//    @Value("${couchbase.timeout.seconds:10}")
    private long timeout=10;

  //  @Value("${couchbase.disabled:false}")
    private boolean disabled=false;

    private CouchbaseCluster cluster;
    private Bucket laundryGuyBucket;
    private Bucket sessionBucket;

    @PostConstruct
    public void init() {
        if (disabled)
            return;

        logger.info("Opening couchbase connection.");
        try {
            CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder()
                    .connectTimeout(timeout * 1000) //10000ms = 10s, default is 5s
                    .build();
            cluster = CouchbaseCluster.create(env, Arrays.asList(hosts.split(",")));
            laundryGuyBucket = cluster.openBucket(CouchbaseConstants.LAUNDRY_GUY_BUCKET);
            sessionBucket = cluster.openBucket(CouchbaseConstants.SESSION_BUCKET);
        } catch (Exception e) {
            cluster = null;
            laundryGuyBucket = null;
            sessionBucket = null;
            throw new IllegalStateException(e);
        }
    }

    @PreDestroy
    public void destroy() {
        logger.info("Closing couchbase connection.");
        if (cluster != null) {
            cluster.disconnect();
        }
    }

    public void setHosts(String hostsIPList) {
        this.hosts = hostsIPList;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public Bucket getLaundryGuyBucket() {
        return laundryGuyBucket;
    }

    public Bucket getSessionBucket() {
        return sessionBucket;
    }


}
