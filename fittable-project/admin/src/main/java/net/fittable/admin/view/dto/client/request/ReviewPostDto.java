package net.fittable.admin.view.dto.client.request;

import lombok.Data;

@Data
public class ReviewPostDto {

    private long studioId;
    private Double rating;
    private String content;
    private String authorsName;

}
