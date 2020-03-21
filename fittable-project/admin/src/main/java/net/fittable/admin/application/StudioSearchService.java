package net.fittable.admin.application;

import net.fittable.admin.infrastructure.repositories.SessionRepository;
import net.fittable.admin.infrastructure.repositories.StudioRepository;
import net.fittable.admin.infrastructure.repositories.TownRepository;
import net.fittable.admin.infrastructure.repositories.search.StudioSearchRepository;
import net.fittable.admin.view.dto.client.request.LocationStudioSearchDto;
import net.fittable.admin.view.dto.client.request.MainpageRequestDto;
import net.fittable.admin.view.dto.client.response.lesson.LessonDto;
import net.fittable.admin.view.dto.client.response.mainpage.MainpageDto;
import net.fittable.admin.view.dto.client.response.studio.StudioDto;
import net.fittable.domain.business.reservation.Session;
import net.fittable.domain.premises.Location;
import net.fittable.domain.search.SearchableStudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StudioSearchService {

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private StudioSearchRepository searchRepository;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public List<Location> getTownList() {
        return townRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<StudioDto> findByTownName(String townName) {
        Location targetLocation = townRepository.findByName(townName).orElseThrow(() -> new NoSuchElementException("부정확한 동네 이름입니다."));

        return studioRepository.findByLocation(targetLocation).stream().map(StudioDto::fromStudio).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<StudioDto> findBySearchConditions(LocationStudioSearchDto dto) {
        List<SearchableStudio> searchedStudios = searchRepository.searchForStudio(dto);

        return searchedStudios.stream()
                .map(s -> studioRepository.findById(s.getId()).orElseThrow(() -> new NoSuchElementException("해당하는 ID에 매칭되는 스튜디오 없음")))
                .map(StudioDto::fromStudio)
                .collect(Collectors.toList());
    }
}
