package net.fittable.admin.view.api;

import net.fittable.admin.application.StudioManagementService;
import net.fittable.admin.application.StudioSearchService;
import net.fittable.admin.view.dto.client.request.LocationStudioSearchDto;
import net.fittable.admin.view.dto.client.request.MainpageRequestDto;
import net.fittable.admin.view.dto.client.response.mainpage.MainpageDto;
import net.fittable.admin.view.dto.client.response.studio.StudioDto;
import net.fittable.domain.premises.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class ClientApi {

    @Autowired
    private StudioManagementService studioManagementService;

    @Autowired
    private StudioSearchService studioSearchService;

    @GetMapping(path = "/{studioId}")
    public StudioDto getStudioById(@PathVariable String studioId) {
        return studioManagementService.getSingleStudio(studioId);
    }

    @GetMapping(path = "/towns")
    public List<Town> getTownList() {
        return studioSearchService.getTownList();
    }

    @PostMapping(path = "/location")
    public List<StudioDto> getStudioByLocation(@RequestBody LocationStudioSearchDto dto) {
        if(dto.isLocationBasedSearch()) {
            return null;
        }

        return studioSearchService.findByTownName(dto.getTownName());
    }
}
