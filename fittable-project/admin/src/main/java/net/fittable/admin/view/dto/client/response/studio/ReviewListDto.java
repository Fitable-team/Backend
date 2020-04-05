package net.fittable.admin.view.dto.client.response.studio;

import lombok.*;
import net.fittable.domain.business.Review;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ReviewListDto {

    private Integer currentPage;
    private boolean hasNextPage;
    private List<Review> reviews;
}
