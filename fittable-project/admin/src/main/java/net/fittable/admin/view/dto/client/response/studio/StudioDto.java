package net.fittable.admin.view.dto.client.response.studio;

import lombok.Data;
import net.fittable.admin.view.dto.client.response.lesson.LessonDto;
import net.fittable.domain.business.Review;
import net.fittable.domain.business.Studio;
import net.fittable.domain.business.StudioFilter;
import net.fittable.domain.business.reservation.Session;
import net.fittable.domain.premises.Coordinate;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StudioDto {

    private Long studioId;
    private String name;
    private String representativeImage;
    private List<String> additionalImageHrefs;
    private List<LessonDto> lessons;
    private List<Session> ongoingSessions;
    private List<Session> allSessions;
    private List<String> studioAttributes;
    private double ratings;
    private String superDistrict;
    private String town;
    private String areaName;
    private IntroductionDto introduction;
    private Coordinate coordinate;
    private int reviewCount;

    public static StudioDto fromStudio(Studio studio) {
        StudioDto studioDto = new StudioDto();

        studioDto.setStudioId((studio.getId()));
        studioDto.setName(studio.getName());
        studioDto.setCoordinate(studio.getCoordinate());
        studioDto.setRepresentativeImage(studio.getImageList().getRepresentativeImage());
        studioDto.setStudioAttributes(studio.getStudioAttributes().stream().map(a -> a.getAttribute().getCaption()).collect(Collectors.toList()));
        studioDto.setAdditionalImageHrefs(studio.getImageList().getImageDirectories());
        studioDto.setAreaName(studio.getLocation().getName());
        studioDto.setOngoingSessions(studio.currentAvailableSessions());
        studioDto.setAllSessions(studio.getAllSessions());
        studioDto.setRatings(studio.getAverageRating());

        if(CollectionUtils.isNotEmpty(studio.getLessons())) {
            studioDto.setLessons(studio.getLessons().stream().map(LessonDto::fromLesson).collect(Collectors.toList()));
        }

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
