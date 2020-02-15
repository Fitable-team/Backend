package net.fittable.admin.view.dto.client.response.lesson;

import lombok.Data;
import net.fittable.domain.business.Lesson;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TimeslotBasedLessons {

    private LocalDateTime timeRange;

    private int countOfLessons;
    private List<String> studioNames;

    public static TimeslotBasedLessons fromLessons(LocalDateTime timeRange, List<Lesson> lessons) {
        TimeslotBasedLessons generatedLessons = new TimeslotBasedLessons();

        generatedLessons.setTimeRange(timeRange);
        generatedLessons.setCountOfLessons(lessons.size());
        generatedLessons.setStudioNames(lessons.stream().map(l -> l.getStudio().getName()).collect(Collectors.toList()));

        return generatedLessons;
    }
}
