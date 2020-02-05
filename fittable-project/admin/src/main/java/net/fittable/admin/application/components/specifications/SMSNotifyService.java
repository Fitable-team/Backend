package net.fittable.admin.application.components.specifications;

import net.fittable.admin.application.components.dto.NotifyResult;
import net.fittable.domain.business.ContactInformation;

import java.util.List;

public interface SMSNotifyService {

    NotifyResult sendToSingle(ContactInformation information);

    NotifyResult sendToGroup(List<ContactInformation> information);

}
