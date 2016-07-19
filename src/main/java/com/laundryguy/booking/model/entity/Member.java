package com.laundryguy.booking.model.entity;

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
    private Byte enabled;

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
    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (id != null ? !id.equals(member.id) : member.id != null) return false;
        if (memberId != null ? !memberId.equals(member.memberId) : member.memberId != null) return false;
        if (emailId != null ? !emailId.equals(member.emailId) : member.emailId != null) return false;
        if (primaryCell != null ? !primaryCell.equals(member.primaryCell) : member.primaryCell != null) return false;
        if (passcode != null ? !passcode.equals(member.passcode) : member.passcode != null) return false;
        if (createdBy != null ? !createdBy.equals(member.createdBy) : member.createdBy != null) return false;
        if (createdAt != null ? !createdAt.equals(member.createdAt) : member.createdAt != null) return false;
        if (enabled != null ? !enabled.equals(member.enabled) : member.enabled != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (memberId != null ? memberId.hashCode() : 0);
        result = 31 * result + (emailId != null ? emailId.hashCode() : 0);
        result = 31 * result + (primaryCell != null ? primaryCell.hashCode() : 0);
        result = 31 * result + (passcode != null ? passcode.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        return result;
    }
}
