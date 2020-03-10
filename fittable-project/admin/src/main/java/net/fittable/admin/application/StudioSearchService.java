package net.fittable.admin.application;

import net.fittable.admin.infrastructure.repositories.SessionRepository;
import net.fittable.admin.infrastructure.repositories.StudioRepository;
import net.fittable.admin.infrastructure.repositories.TownRepository;
import net.fittable.admin.view.dto.client.request.MainpageRequestDto;
import net.fittable.admin.view.dto.client.response.lesson.LessonDto;
import net.fittable.admin.view.dto.client.response.mainpage.MainpageDto;
import net.fittable.admin.view.dto.client.response.studio.StudioDto;
import net.fittable.domain.business.reservation.Session;
import net.fittable.domain.premises.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StudioSearchService {

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public List<Location> getTownList() {
        return townRepository.findAll();
    }

    @Transactional
    public List<StudioDto> findByTownName(String townName) {
        Location targetLocation = townRepository.findByName(townName).orElseThrow(() -> new NoSuchElementException("부정확한 동네 이름입니다."));

        return studioRepository.findByLocation(targetLocation).stream().map(StudioDto::fromStudio).collect(Collectors.toList());
    }

    @Transactional
    public MainpageDto generateMainPage(MainpageRequestDto requestDto) {
        List<Session> sessions = sessionRepository.findByStartTimeAfter(requestDto.getSince());
        List<LessonDto> lessons = sessions.stream()
                .map(Session::getLesson)
                .map(LessonDto::fromLesson).collect(Collectors.toList());

        MainpageDto dto = new MainpageDto();
        dto.setLessons(lessons);
        dto.setTownName("서현");
        dto.setSelectedDate(LocalDate.now());

        return dto;
    }
}
