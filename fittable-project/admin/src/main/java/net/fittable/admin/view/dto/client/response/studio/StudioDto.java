package net.fittable.admin.view.dto.client.response.studio;

import lombok.Data;
import net.fittable.domain.business.Review;
import net.fittable.domain.business.Studio;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

@Data
public class StudioDto {

    private String studioId;
    private String name;
    private double ratings;
    private String town;
    private String areaName;
    private IntroductionDto introduction;
    private List<Review> reviews;
    private int reviewCount;

    public static StudioDto fromStudio(Studio studio) {
        StudioDto studioDto = new StudioDto();

        studioDto.setStudioId(String.valueOf(studio.getId()));
        studioDto.setName(studio.getName());
        studioDto.setAreaName(studio.getLocation().getName());

        if(CollectionUtils.isNotEmpty(studio.getReviews())) {
            studioDto.setRatings(studio.getReviews().stream().mapToDouble(Review::getStarPoint).average().getAsDouble());
        }

        if(studio.getLocation() != null) {
            studioDto.setTown(studio.getLocation().getSuperDistrict() + " " + studio.getLocation().getLowerDistrict());
        }

        studioDto.setIntroduction(IntroductionDto.fromStudio(studio));

        studioDto.setReviews(studio.getReviews());
        studioDto.setReviewCount(studio.getReviews().size());

        return studioDto;
    }
}
