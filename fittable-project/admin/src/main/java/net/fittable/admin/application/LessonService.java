package net.fittable.admin.application;

import net.fittable.admin.application.components.TimeFormatter;
import net.fittable.admin.infrastructure.repositories.SessionRepository;
import net.fittable.admin.view.dto.client.request.LessonSearchDto;
import net.fittable.admin.view.dto.client.response.lesson.LessonDto;
import net.fittable.domain.business.reservation.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class LessonService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private TimeFormatter timeFormatter;

    @Transactional(readOnly = true)
    public List<Session> findLessons(LessonSearchDto dto) {
        LocalDateTime startTime = timeFormatter.parseTime(dto.getFrom());
        LocalDateTime endTime = timeFormatter.parseTime(dto.getUntil());

        List<Session> foundSessions = sessionRepository.findByStartTimeAfterAndEndTimeBefore(startTime, endTime);

        if(dto.containsTargetStudioIds()) {
            return foundSessions.stream()
                    .filter(s -> dto.getTargetStudioIds().contains(String.valueOf(s.getTargetStudio().getId())))
                    .collect(Collectors.toList());
        }
        return foundSessions;
    }
}
