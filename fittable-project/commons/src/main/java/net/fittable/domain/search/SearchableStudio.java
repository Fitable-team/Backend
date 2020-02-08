package net.fittable.domain.search;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.fittable.domain.business.Studio;

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
                .town(studio.getTown().getName())
                .superDistrict(studio.getTown().getSuperDistrict())
                .lowerDistrict(studio.getTown().getLowerDistrict())
                .latitude(studio.getCoordinate().getLatitude())
                .longitude(studio.getCoordinate().getLongitude())
                .build();
    }
}
