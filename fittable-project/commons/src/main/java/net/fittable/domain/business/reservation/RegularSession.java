package net.fittable.domain.business.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Embeddable
@Data
@NoArgsConstructor
public class RegularSession {
    private static final String DAY_DELIMITER = ";";

    @JsonIgnore
    private String regularDays;

    public void addRegularDay(String koreanDay) {
        if(this.regularDays == null || this.regularDays.length() == 0) {
            this.regularDays = koreanDay;

            return;
        }
        String newDay = regularDays;
        this.regularDays = newDay.concat(DAY_DELIMITER).concat(koreanDay);
    }

    @JsonProperty("repeatDays")
    public Set<Weekday> getWeekdayEnums() {
        return Arrays.stream(this.regularDays.split(DAY_DELIMITER))
                .map(Weekday::getByKoreanDay)
                .collect(Collectors.toSet());
    }

    public boolean isClassScheduledToday() {
        return this.getWeekdayEnums().stream().anyMatch(w -> w.getDayOfWeek().equals(LocalDate.now().getDayOfWeek()));
    }

    public boolean hasRegularSessions() {
        return !StringUtils.isEmpty(this.regularDays);
    }

    public RegularSession(List<String> repeatDays) {
        this.regularDays = String.join(DAY_DELIMITER, repeatDays);
    }
}
