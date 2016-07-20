package com.laundryguy.booking.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by maninder on 20/7/16.
 */
@Component
public class DaoBase {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }

}
