package net.fittable.admin.view.dto.client.request;

import lombok.Data;
import net.fittable.domain.business.Review;

@Data
public class ReviewPostDto {

    private long studioId;
    private Double rating;
    private String content;

}
