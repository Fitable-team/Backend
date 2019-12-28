package net.fittable.domain.business;

import javax.persistence.*;

@Entity
@Table(name = "BUSINESS_OWNER")
public class BusinessOwner {

    @Id
    @GeneratedValue
    @Column(name = "BUSINESS_ONWER_ID")
    private String id;

    @Column(name = "BUSINESS_OWNER_NAME")
    private String name;

    @Embedded
    private ContactInformation contactInformation;

    public BusinessOwner(String name, ContactInformation contactInformation) {
        this.name = name;
        this.contactInformation = contactInformation;
    }
}
