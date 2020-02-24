package net.fittable.admin.view.dto.client.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LocationStudioSearchDto {

    private LocalDateTime since;
    private LocalDateTime until;
    private double latitude;
    private double longitude;
    private String townName;

    public boolean isLocationBasedSearch() {
        return latitude != 0.0 && longitude != 0.0;
    }
}
