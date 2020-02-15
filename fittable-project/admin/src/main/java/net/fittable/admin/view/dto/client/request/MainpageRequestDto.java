package net.fittable.admin.view.dto.client.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MainpageRequestDto {

    private LocalDateTime since;
    private LocalDateTime until;
    private double latitude;
    private double longitude;
}
