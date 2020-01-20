package net.fittable.domain.authentication;

import lombok.Builder;
import net.fittable.domain.authentication.enums.MemberAuthority;
import net.fittable.domain.authentication.enums.SocialProvider;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER")
public class ClientMember implements Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_SEQUENCE")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "CLIENT_SOCIAL_PROVIDER")
    private SocialProvider socialProvider;

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

    @Builder
    public ClientMember(String loginId, String encryptedPassword, LocalDateTime birthday, String phoneNumber, String emailAddress) {
        this.loginId = loginId;
        this.encryptedPassword = encryptedPassword;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

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
