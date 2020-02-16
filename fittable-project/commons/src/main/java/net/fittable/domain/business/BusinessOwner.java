package net.fittable.domain.business;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.fittable.domain.authentication.AdminMember;

import javax.persistence.*;

@Entity
@Table(name = "BUSINESS_OWNER")
@Data
@NoArgsConstructor
public class BusinessOwner {

    @Id
    @GeneratedValue
    @Column(name = "BUSINESS_ONWER_ID")
    private String id;

    @Column(name = "BUSINESS_OWNER_NAME")
    private String name;

    @OneToOne(mappedBy = "owner")
    private Studio studio;

    @ManyToOne
    @JoinColumn(name = "ADMIN_MEMBER_ID")
    private AdminMember adminMember;

    @Embedded
    private ContactInformation contactInformation;

    public BusinessOwner(String name, ContactInformation contactInformation) {
        this.name = name;
        this.contactInformation = contactInformation;
    }
}
