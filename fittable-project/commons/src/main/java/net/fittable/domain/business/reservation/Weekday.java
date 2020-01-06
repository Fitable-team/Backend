package net.fittable.domain.business.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Calendar;

@AllArgsConstructor
@Getter
public enum Weekday {

    MONDAY("월요일", Calendar.MONDAY),
    TUESDAY("화요일", Calendar.TUESDAY),
    WEDNESDAY("수요일", Calendar.WEDNESDAY),
    THURSDAY("목요일", Calendar.THURSDAY),
    FRIDAY("금요일", Calendar.FRIDAY),
    SATURDAY("토요일", Calendar.SATURDAY),
    SUNDAY("일요일", Calendar.SUNDAY);

    private String koreanDay;
    private int ordinalValue;

    public static Weekday getByKoreanDay(String koreanDay) {
        return Arrays.stream(Weekday.values())
                .filter(day -> day.getKoreanDay().equals(koreanDay))
                .findFirst()
                .orElse(SUNDAY);
    }
}
