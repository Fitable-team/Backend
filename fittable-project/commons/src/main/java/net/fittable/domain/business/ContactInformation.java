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
    private static final String CONTACT_INFORMATION_DELIMITER = ";";

    @Enumerated(EnumType.STRING)
    @Column(name = "CONTACT_TYPE")
    private ContactInformationType informationType;

    @Column(name = "CONTACT")
    private String contactInformationValue;

    public void addPhoneNumber(String phoneNumber) {
        if(this.contactInformationValue == null || this.contactInformationValue.isEmpty()) {

        }
    }
}
