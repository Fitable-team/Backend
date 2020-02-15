package net.fittable.admin.view.dto.client.response.studio;

import lombok.Data;
import net.fittable.admin.view.dto.client.response.studio.StudioDto;

import java.util.List;

@Data
public class StudioSearchResultDto {

    private List<StudioDto> studios;
}
