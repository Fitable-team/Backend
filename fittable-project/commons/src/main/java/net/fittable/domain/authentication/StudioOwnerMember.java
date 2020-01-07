package net.fittable.domain.authentication;

import lombok.Data;
import net.fittable.domain.authentication.enums.MemberAuthority;
import net.fittable.domain.business.BusinessOwner;
import net.fittable.domain.business.ContactInformation;
import net.fittable.domain.business.Studio;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "STORE_MEMBER")
@Data
public class StudioOwnerMember implements Member {

    @Id
    @GeneratedValue
    @Column(name = "STORE_MEMBER_ID")
    private long id;

    @Column(name = "STORE_MEMBER_LOGINID")
    private String loginId;

    @Column(name = "STORE_MEMBER_PW")
    private String encryptedPassword;

    @Embedded
    private ContactInformation ownerContactInformation;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Studio> studios;

    @Override
    public String getLoginId() {
        return this.loginId;
    }

    @Override
    public LocalDateTime getBirthday() {
        return LocalDateTime.now();
    }

    @Override
    public String getPhoneNumber() {
        return this.ownerContactInformation.getRepresentativeContact();
    }

    @Override
    public String getEmailAddress() {
        return null;
    }

    @Override
    public MemberAuthority getAuthority() {
        return MemberAuthority.STUDIO_OWNER;
    }

    @Override
    public boolean isMatchingPassword(String encryptedPassword) {
        return this.encryptedPassword.equals(encryptedPassword);
    }
}
