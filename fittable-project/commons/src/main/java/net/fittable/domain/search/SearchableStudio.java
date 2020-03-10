package net.fittable.domain.search;

import lombok.*;
import net.fittable.domain.business.Studio;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Data
public class SearchableStudio {

    private String name;
    private String town;
    private String superDistrict;
    private String lowerDistrict;
    private double latitude;
    private double longitude;

    public static SearchableStudio fromStudio(Studio studio) {
        return SearchableStudio.builder()
                .name(studio.getName())
                .town(studio.getLocation().getName())
                .superDistrict(studio.getLocation().getSuperDistrict())
                .lowerDistrict(studio.getLocation().getLowerDistrict())
                .latitude(studio.getCoordinate().getLatitude())
                .longitude(studio.getCoordinate().getLongitude())
                .build();
    }

    public static SearchableStudio emptyObject() {
        return new SearchableStudio();
    }
}
