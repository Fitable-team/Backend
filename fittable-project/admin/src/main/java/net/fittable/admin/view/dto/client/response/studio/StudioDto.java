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
    private IntroductionDto introduction;
    private List<Review> reviews;
    private int reviewCount;

    public static StudioDto fromStudio(Studio studio) {
        StudioDto studioDto = new StudioDto();

        studioDto.setStudioId(String.valueOf(studio.getId()));
        studioDto.setName(studio.getName());

        if(CollectionUtils.isNotEmpty(studio.getReviews())) {
            studioDto.setRatings(studio.getReviews().stream().mapToDouble(Review::getStarPoint).average().getAsDouble());
        }

        if(studio.getTown() != null) {
            studioDto.setTown(studio.getTown().getSuperDistrict() + " " + studio.getTown().getLowerDistrict());
        }
        
        studioDto.setIntroduction(IntroductionDto.fromStudio(studio));

        studioDto.setReviews(studio.getReviews());
        studioDto.setReviewCount(studio.getReviews().size());

        return studioDto;
    }
}
