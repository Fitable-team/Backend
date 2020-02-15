package net.fittable.admin.view.dto.client.response.mainpage;

import lombok.Data;
import net.fittable.admin.view.dto.client.response.lesson.LessonDto;

import java.time.LocalDate;
import java.util.List;

@Data
public class MainpageDto {

    private String townName;
    private LocalDate selectedDate;
    private List<LessonDto> lessons;
}
