package net.fittable.admin.application.components;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeFormatter {
    private static final DateTimeFormatter STANDARD_API_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss");

    public LocalDateTime parseTime(String timeString) {
        return LocalDateTime.from(STANDARD_API_TIME_FORMAT.parse(timeString));
    }

}
