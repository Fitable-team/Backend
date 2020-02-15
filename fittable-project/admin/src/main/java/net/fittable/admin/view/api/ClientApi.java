package net.fittable.admin.view.api;

import net.fittable.admin.application.StudioManagementService;
import net.fittable.admin.application.StudioSearchService;
import net.fittable.admin.infrastructure.repositories.StudioRepository;
import net.fittable.admin.infrastructure.repositories.TownRepository;
import net.fittable.admin.view.dto.client.LocationStudioSearchDto;
import net.fittable.domain.business.Studio;
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
    public Studio getStudioById(@PathVariable String studioId) {
        return studioManagementService.getSingleStudio(studioId);
    }

    @GetMapping(path = "/towns")
    public List<Town> getTownList() {
        return studioSearchService.getTownList();
    }

    @PostMapping(path = "/location")
    public List<Studio> getStudioByLocation(@RequestBody LocationStudioSearchDto dto) {
        if(dto.isLocationBasedSearch()) {
            return null;
        }

        return studioSearchService.findByTownName(dto.getTownName());
    }
}
