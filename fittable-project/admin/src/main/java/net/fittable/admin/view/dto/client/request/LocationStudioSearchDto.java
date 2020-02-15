package net.fittable.admin.view.dto.client.request;

import lombok.Data;

@Data
public class LocationStudioSearchDto {

    private double latitude;
    private double longitude;
    private String townName;

    public boolean isLocationBasedSearch() {
        return latitude != 0.0 && longitude != 0.0;
    }
}
