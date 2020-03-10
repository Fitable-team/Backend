package net.fittable.admin.view.dto.client.response.studio;

import lombok.Data;
import net.fittable.domain.business.SocialAddress;
import net.fittable.domain.business.Studio;

@Data
public class IntroductionDto {

    private String detailedAddress;
    private String contact;
    private SocialAddress socialAddress;
    private String introduction;

    public static IntroductionDto fromStudio(Studio studio) {
        IntroductionDto dto = new IntroductionDto();

        dto.setContact(studio.getRepresentativeContact());
        dto.setDetailedAddress(studio.getDetailedAddress());
        dto.setSocialAddress(studio.getSocialAddress());
        dto.setIntroduction(studio.getStudioIntroduction());

        return dto;
    }
}
