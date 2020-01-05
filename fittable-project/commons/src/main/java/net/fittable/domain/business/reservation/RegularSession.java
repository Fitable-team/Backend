package net.fittable.domain.business.reservation;

import lombok.Data;

import javax.persistence.Embeddable;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Embeddable
@Data
public class RegularSession {
    private static final String DAY_DELIMITER = ";";

    private String regularDays;

    public void addRegularDay(String koreanDay) {
        if(this.regularDays == null || this.regularDays.length() == 0) {
            this.regularDays = koreanDay;

            return;
        }
        String newDay = regularDays;
        newDay.concat(DAY_DELIMITER).concat(koreanDay);
        this.regularDays = newDay;
    }

    public Set<Weekday> getWeekdayEnums() {
        return Arrays.stream(this.regularDays.split(DAY_DELIMITER))
                .map(day -> Weekday.getByKoreanDay(day))
                .collect(Collectors.toSet());
    }
}