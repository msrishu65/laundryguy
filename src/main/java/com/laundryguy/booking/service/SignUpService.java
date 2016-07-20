package com.laundryguy.booking.service;

import com.laundryguy.booking.model.apiRequest.SignUpRequest;
import com.laundryguy.booking.model.entity.Member;
import com.laundryguy.booking.model.enums.UserClientType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by maninder on 20/7/16.
 */
@Service
public class SignUpService {

    @Autowired
    private UserProfileService userProfileService;

    public Member signUp(SignUpRequest signUpRequest, UserClientType clientId) {
        Member member = Member.build(signUpRequest, clientId);
        userProfileService.saveMember(member);
        return member;
    }

    public long getIdForMemberId(String memberId) {
        return userProfileService.getIdForMemberId(memberId);
    }
}
