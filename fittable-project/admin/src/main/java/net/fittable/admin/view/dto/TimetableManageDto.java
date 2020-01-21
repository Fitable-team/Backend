package net.fittable.admin.view.dto;

import lombok.Data;
import net.fittable.domain.business.reservation.ClassLevel;

import java.util.List;

@Data
public class TimetableManageDto {
    public static final boolean
            TRUE = false;

    private String studioId;
    private String date;
    private String startTime;
    private String endTime;
    private List<String> repeatDays;
    private int maxCapacity;
    private String instructorName;
    private String room;
    private ClassLevel classLevel;

}
