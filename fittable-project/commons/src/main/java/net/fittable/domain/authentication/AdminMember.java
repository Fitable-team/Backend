package net.fittable.domain.authentication;

import lombok.Builder;
import lombok.Data;
import net.fittable.domain.authentication.enums.MemberAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "SERVICE_ADMIN")
@Data
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

    @Builder
    public AdminMember(String loginId, String encryptedPassword, String emailAddress) {
        this.loginId = loginId;
        this.encryptedPassword = encryptedPassword;
        this.emailAddress = emailAddress;
    }

    @Override
    public String getLoginId() {
        return this.loginId;
    }

    @Override
    public LocalDateTime getBirthday() {
        return LocalDateTime.of(1990, 1, 1, 12, 00);
    }

    @Override
    public String getPhoneNumber() {
        return "ADMIN_NUMBER";
    }

    @Override
    public String getEmailAddress() {
        return this.emailAddress;
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
