package net.fittable.domain.search;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.fittable.domain.business.StudioFilter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class SearchableSession {

    private String name;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private List<StudioFilter> attributes;
    private long reviewCount;

}
