package net.fittable.domain.authentication;

import net.fittable.domain.authentication.enums.MemberAuthority;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "SERVICE_ADMIN")
public class AdminMember implements Member {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "ADMIN_MEMBER")
    private String loginId;

    @Column(name = "ADMIN_PASSWORD")
    private String encryptedPassword;

    @Column(name = "ADMIN_EMAIL")
    private String emailAddress;

    @Override
    public String getLoginId() {
        return null;
    }

    @Override
    public LocalDateTime getBirthday() {
        return null;
    }

    @Override
    public String getPhoneNumber() {
        return null;
    }

    @Override
    public String getEmailAddress() {
        return null;
    }

    @Override
    public MemberAuthority getAuthority() {
        return MemberAuthority.ADMIN;
    }

    @Override
    public boolean isMatchingPassword(String encryptedPassword) {
        return encryptedPassword.equals(this.encryptedPassword);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminMember that = (AdminMember) o;
        return Objects.equals(loginId, that.loginId) &&
                Objects.equals(emailAddress, that.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginId, emailAddress);
    }
}
