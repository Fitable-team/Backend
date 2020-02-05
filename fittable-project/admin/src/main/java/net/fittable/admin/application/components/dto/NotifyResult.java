package net.fittable.admin.application.components.dto;

import lombok.Data;
import net.fittable.domain.business.ContactInformation;

import java.time.LocalDateTime;
import java.util.List;

@Data
public abstract class NotifyResult {

    private LocalDateTime issuedDateTime;

    abstract boolean wasSuccessful();
    abstract List<ContactInformation> getTargets();
}
