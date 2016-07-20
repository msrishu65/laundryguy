package com.laundryguy.booking.model.entity;

import com.laundryguy.booking.model.apiRequest.SignUpRequest;
import com.laundryguy.booking.model.enums.UserClientType;
import com.laundryguy.booking.utils.HashingUtil;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by maninder on 20/7/16.
 */
@Entity
@Table(name = "member")
public class Member {
    private Integer id;
    private String memberId;
    private String emailId;
    private String primaryCell;
    private String passcode;
    private String createdBy;
    private Timestamp createdAt;
    private boolean enabled;

    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "MemberID", nullable = false, length = 50)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "EmailID", nullable = true, length = 50)
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Basic
    @Column(name = "primaryCell", nullable = true, length = 15)
    public String getPrimaryCell() {
        return primaryCell;
    }

    public void setPrimaryCell(String primaryCell) {
        this.primaryCell = primaryCell;
    }

    @Basic
    @Column(name = "Passcode", nullable = false, length = 70)
    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    @Basic
    @Column(name = "CreatedBy", nullable = true, length = 32)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "CreatedAt", nullable = true)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public static Member build(SignUpRequest signUpRequest, UserClientType clientId) {
        Member member = new Member();
        member.setMemberId(signUpRequest.getMemberId());
        member.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        member.setCreatedBy(String.valueOf(clientId.getIdentifier()));
        member.setEnabled(true);
        member.setPasscode(HashingUtil.encodeMD5AndBecrypt(signUpRequest.getPassword()));
        member.setPrimaryCell(signUpRequest.getCell());
        return member;
    }
}
