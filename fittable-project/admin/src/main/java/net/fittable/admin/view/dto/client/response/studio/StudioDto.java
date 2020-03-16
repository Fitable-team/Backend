package net.fittable.admin.view.dto.client.response.studio;

import lombok.Data;
import net.fittable.domain.business.Review;
import net.fittable.domain.business.Studio;
import net.fittable.domain.premises.Coordinate;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

@Data
public class StudioDto {

    private String studioId;
    private String name;
    private String representativeImage;
    private List<String> additionalImageHrefs;
    private double ratings;
    private String superDistrict;
    private String town;
    private String areaName;
    private IntroductionDto introduction;
    private Coordinate coordinate;
    private int reviewCount;

    public static StudioDto fromStudio(Studio studio) {
        StudioDto studioDto = new StudioDto();

        studioDto.setStudioId(String.valueOf(studio.getId()));
        studioDto.setName(studio.getName());
        studioDto.setCoordinate(studio.getCoordinate());
        studioDto.setRepresentativeImage(studio.getImageList().getRepresentativeImage());
        studioDto.setAdditionalImageHrefs(studio.getImageList().getImageDirectories());
        studioDto.setAreaName(studio.getLocation().getName());

        if(CollectionUtils.isNotEmpty(studio.getReviews())) {
            studioDto.setRatings(studio.getReviews().stream().mapToDouble(Review::getStarPoint).average().getAsDouble());
        }

        if(studio.getLocation() != null) {
            studioDto.setTown(studio.getLocation().getSuperDistrict() + " " + studio.getLocation().getLowerDistrict());
            studioDto.setSuperDistrict(studio.getLocation().getSuperDistrict());
        }

        studioDto.setIntroduction(IntroductionDto.fromStudio(studio));
        studioDto.setReviewCount(studio.getReviews().size());

        return studioDto;
    }
}
