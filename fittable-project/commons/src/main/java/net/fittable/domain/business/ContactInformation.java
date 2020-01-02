package net.fittable.domain.business;

import lombok.Data;
import net.fittable.domain.business.enums.ContactInformationType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
public class ContactInformation {

    @Enumerated(EnumType.STRING)
    @Column(name = "CONTACT_TYPE")
    private ContactInformationType informationType;

    @Column(name = "CONTACT")
    private String contactInformationValue;
}
