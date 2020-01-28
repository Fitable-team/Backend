package net.fittable.admin.view.dto;

import lombok.*;
import net.fittable.domain.business.reservation.ClassLevel;
import net.fittable.domain.business.reservation.RegularSession;
import net.fittable.domain.business.reservation.Session;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class TimetableManageDto {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    private String studioId;
    private String classId;
    private String date;
    private String startTime;
    private String endTime;
    private List<String> repeatDays;
    private int maxCapacity;
    private String instructorName;
    private String room;
    private int price;
    private ClassLevel classLevel;

    public boolean isCreatingNewTimeSession() {
        return StringUtils.isEmpty(this.classId);
    }

    public Session toSession() {
        Session.SessionBuilder builder = Session.builder()
                .capacity(maxCapacity)
                .startTime(LocalDateTime.from(TIME_FORMATTER.parse(this.startTime)))
                .endTime(LocalDateTime.from(TIME_FORMATTER.parse(this.endTime)))
                .room(this.room)
                .classLevel(this.classLevel)
                .price(this.price)
                .instructorName(this.instructorName);

        if(this.repeatDays.size() > 0) {
            Session session = builder.build();
            session.setRegularSession(new RegularSession(this.repeatDays));

            return session;
        }

        return builder.build();
    }
}
