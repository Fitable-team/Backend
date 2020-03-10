package net.fittable.admin.view.api;

import net.fittable.admin.application.LessonService;
import net.fittable.admin.application.StudioManagementService;
import net.fittable.admin.application.StudioSearchService;
import net.fittable.admin.view.dto.client.request.LessonSearchDto;
import net.fittable.admin.view.dto.client.request.LocationStudioSearchDto;
import net.fittable.admin.view.dto.client.response.studio.StudioDto;
import net.fittable.domain.business.reservation.Session;
import net.fittable.domain.premises.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class ClientApi {

    @Autowired
    private StudioManagementService studioManagementService;

    @Autowired
    private StudioSearchService studioSearchService;

    @Autowired
    private LessonService lessonService;

    @GetMapping(path = "/studios/{studioId}")
    public StudioDto getStudioById(@PathVariable String studioId) {
        return studioManagementService.getSingleStudio(studioId);
    }

    @GetMapping(path = "/towns")
    public List<Location> getTownList() {
        return studioSearchService.getTownList();
    }

    @PostMapping(path = "/location")
    public List<StudioDto> getStudioByLocation(@RequestBody LocationStudioSearchDto dto) {
        if(dto.isLocationBasedSearch()) {
            return null;
        }
        return studioSearchService.findByTownName(dto.getTownName());
    }

    @GetMapping(path = "/classes/{studioIds}")
    public List<Session> getLessons(@PathVariable String studioIds, @RequestParam String from, @RequestParam String until) {
        LessonSearchDto dto = new LessonSearchDto();

        dto.setFrom(from);
        dto.setUntil(until);

        dto.setTargetStudioIds(Arrays.asList(studioIds.split(",")));

        return lessonService.findLessons(dto);
    }
}
