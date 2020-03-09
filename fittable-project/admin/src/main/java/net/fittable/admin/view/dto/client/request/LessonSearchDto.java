package net.fittable.admin.view.dto.client.request;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LessonSearchDto {

    private List<String> targetStudioIds;
    private String from;
    private String until;

    public boolean containsTargetStudioIds() {
        return CollectionUtils.isNotEmpty(this.targetStudioIds);
    }
}
