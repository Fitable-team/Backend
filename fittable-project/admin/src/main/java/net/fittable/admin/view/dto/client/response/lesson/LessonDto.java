package net.fittable.admin.view.dto.client.response.lesson;

import lombok.Data;
import net.fittable.admin.view.dto.client.response.studio.StudioDto;
import net.fittable.domain.business.Lesson;
import net.fittable.domain.business.reservation.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LessonDto {

    private String name;
    private List<Session> allSessions;
    private List<Session> availableSessions;
    private String instructorName;
    private StudioDto studioInfo;

    public static LessonDto fromLesson(Lesson lesson) {
        LessonDto dto = new LessonDto();

        dto.setName(lesson.getTitle());
        dto.setAllSessions(new ArrayList<>(lesson.getSessions()));
        dto.setAvailableSessions(lesson.getSessions().stream().filter(s -> !s.isFullyBooked()).collect(Collectors.toList()));
        dto.setInstructorName(lesson.getInstructorName());
        dto.setStudioInfo(StudioDto.fromStudio(lesson.getStudio()));

        return dto;
    }
}
