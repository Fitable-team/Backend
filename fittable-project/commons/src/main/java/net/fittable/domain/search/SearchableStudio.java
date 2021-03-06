package net.fittable.domain.search;

import lombok.*;
import net.fittable.domain.business.Studio;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Data
public class SearchableStudio {

    private Long id;
    private String name;
    private String town;
    private String superDistrict;
    private String lowerDistrict;
    private LocationMapping location;

    public static SearchableStudio fromStudio(Studio studio) {
        LocationMapping locationMapping = LocationMapping.builder().lat(studio.getCoordinate().getLatitude()).lon(studio.getCoordinate().getLongitude()).build();

        return SearchableStudio.builder()
                .id(studio.getId())
                .name(studio.getName())
                .town(studio.getLocation().getName())
                .superDistrict(studio.getLocation().getSuperDistrict())
                .lowerDistrict(studio.getLocation().getLowerDistrict())
                .location(locationMapping)
                .build();
    }

    public static SearchableStudio emptyObject() {
        return new SearchableStudio();
    }
}
