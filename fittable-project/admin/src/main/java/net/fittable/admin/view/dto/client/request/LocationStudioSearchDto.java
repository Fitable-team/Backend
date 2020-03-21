package net.fittable.admin.view.dto.client.request;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Data
public class LocationStudioSearchDto {

    private double latitude;
    private double longitude;
    private String withinKilometer;
    private String townName;

    public boolean isLocationBasedSearch() {
        return latitude != 0.0 && longitude != 0.0 && !StringUtils.isEmpty(this.withinKilometer);
    }

}
