package net.fittable.domain.authentication;

import net.fittable.domain.authentication.enums.MemberAuthority;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "MEMBER")
public class ClientMember implements Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_SEQUENCE")
    private long id;

    @Column(name = "MEMBER_ID")
    private String loginId;

    @Column(name = "MEMBER_PASSWORD")
    private String encryptedPassword;

    @Column(name = "BIRTHDAY")
    private LocalDateTime birthday;

    @Column(name = "MOBILE_NUMBER")
    private String phoneNumber;

    @Column(name = "MEMBER_EMAIL")
    private String emailAddress;

    @Override
    public String getLoginId() {
        return this.loginId;
    }

    @Override
    public LocalDateTime getBirthday() {
        return this.birthday;
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public String getEmailAddress() {
        return this.emailAddress;
    }

    @Override
    public MemberAuthority getAuthority() {
        return MemberAuthority.MEMBER;
    }

    @Override
    public boolean isMatchingPassword(String encryptedPassword) {
        return encryptedPassword.equals(this.encryptedPassword);
    }
}
