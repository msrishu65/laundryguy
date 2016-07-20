package com.laundryguy.booking.service;

import com.laundryguy.booking.dao.UserProfileDao;
import com.laundryguy.booking.model.entity.Member;
import com.laundryguy.booking.utils.HashingUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by maninder on 20/7/16.
 */
@Service
public class UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Transactional
    public long getIdForMemberId(String memberId) {
        return userProfileDao.getIdForMemberId(memberId);
    }

    @Transactional
    public void saveMember(Member member) {
        userProfileDao.saveMember(member);
    }

    @Transactional
    public Member getMember(String memberId) {
        return userProfileDao.getMember(memberId);
    }

    @Transactional
    public Member getMemberFromCell(String contactNo) {
        return userProfileDao.getMemberFromCell(contactNo);
    }

    public boolean isValidPassword(Member member, String plainTextPassword) {
        return HashingUtil.doesHashMatch(plainTextPassword, member.getPasscode());
    }

}
