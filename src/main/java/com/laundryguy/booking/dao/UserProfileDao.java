package com.laundryguy.booking.dao;

import com.laundryguy.booking.model.entity.Member;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by maninder on 20/7/16.
 */
@Repository
public class UserProfileDao extends DaoBase {

    private static Logger logger = LogManager.getLogger(UserProfileDao.class);

    public long getIdForMemberId(String memberId) {
        //we are using jdbc tempalte becuase with hibernate id was not getting auto incremented in hibernate session persistent object
        String sql = "select ID from member where MemberID = ?";
        long id = jdbcTemplate.queryForObject(sql,
                new Object[]{memberId}, Integer.class);
        return id;
    }

    public void saveMember(Member member) {
        getCurrentSession().save(member);
    }

    public Member getMember(String memberId) {
        return (Member) getCurrentSession().get(Member.class, memberId);
    }

    public Member getMemberFromCell(String contactNo) {
        String hql = "From Member where primaryCell=:cell";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("cell", contactNo);
        Member member = (Member) query.uniqueResult();
        return member;
    }
}
