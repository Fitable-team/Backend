package net.fittable.domain.business;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.fittable.domain.business.enums.ContactInformationType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Embeddable
@Data
@NoArgsConstructor
public class ContactInformation {
    private static final String CONTACT_INFORMATION_DELIMITER = ";";

    @Enumerated(EnumType.STRING)
    @Column(name = "CONTACT_TYPE")
    private ContactInformationType informationType;

    @Column(name = "CONTACT")
    private String contactInformationValue;

    @Column(name = "REPRESENTATIVE_CONTACT")
    private String representativeContact;

    @Builder
    public ContactInformation(ContactInformationType informationType, String contactInformationValue) {
        this.informationType = informationType;
        this.contactInformationValue = contactInformationValue;
    }

    public void addPhoneNumber(String phoneNumber) {
        if(this.contactInformationValue == null || this.contactInformationValue.isEmpty()) {
            this.contactInformationValue = phoneNumber;
        }
        StringBuilder concatenatedPhoneNumber = new StringBuilder(this.contactInformationValue);
        concatenatedPhoneNumber.append(CONTACT_INFORMATION_DELIMITER);
        concatenatedPhoneNumber.append(phoneNumber);

        this.contactInformationValue = concatenatedPhoneNumber.toString();
    }

    public List<String> getPhoneNumbers() {
        if(this.contactInformationValue.contains(CONTACT_INFORMATION_DELIMITER)) {
            return Arrays.asList(this.contactInformationValue.split(CONTACT_INFORMATION_DELIMITER));
        }
        return Arrays.asList(this.contactInformationValue);
    }
}
