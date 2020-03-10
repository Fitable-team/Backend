package net.fittable.admin.view.dto;

import lombok.Data;
import net.fittable.domain.business.SocialAddress;
import net.fittable.domain.business.Studio;
import net.fittable.domain.business.StudioImageList;
import net.fittable.domain.premises.Location;

import java.util.List;

@Data
public class StudioEditDto {

    private String studioName;
    private String profileImageDir;
    private String address;
    private Location location;
    private String telephone;
    private SocialAddress socialAddress;
    private String representativeImageDir;
    private List<String> otherImagesDir;
    private String introduction;
    private String notice;
    private String directions;

    public Studio toEntity() {
        StudioImageList imageList = new StudioImageList(this.representativeImageDir, this.otherImagesDir);

        return Studio.builder()
                .name(this.studioName)
                .detailedAddress(this.address)
                .imageList(imageList)
                .directions(this.directions)
                .notice(this.notice)
                .socialAddress(this.socialAddress)
                .location(this.location)
                .build();
    }

}
