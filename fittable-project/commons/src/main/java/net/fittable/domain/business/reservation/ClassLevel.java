package net.fittable.domain.business.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClassLevel {
    LV_1("초급"),
    LV_2("중급"),
    LV_3("고급");

    private String koreanLevel;


}
