package net.fittable.domain.business.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;
import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Weekday {

    MONDAY("월요일", DayOfWeek.MONDAY),
    TUESDAY("화요일",  DayOfWeek.TUESDAY),
    WEDNESDAY("수요일", DayOfWeek.WEDNESDAY),
    THURSDAY("목요일",  DayOfWeek.THURSDAY),
    FRIDAY("금요일", DayOfWeek.FRIDAY),
    SATURDAY("토요일", DayOfWeek.SATURDAY),
    SUNDAY("일요일", DayOfWeek.SUNDAY);

    private String koreanDay;
    private DayOfWeek dayOfWeek;

    public static Weekday getByKoreanDay(String koreanDay) {
        return Arrays.stream(Weekday.values())
                .filter(day -> day.getKoreanDay().equals(koreanDay))
                .findFirst()
                .orElse(SUNDAY);
    }
}
