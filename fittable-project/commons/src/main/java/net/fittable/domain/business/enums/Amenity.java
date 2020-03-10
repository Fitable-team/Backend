package net.fittable.domain.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Getter
public enum Amenity {
    MAT_RENTAL("매트대여"),
    YOGA_WEAR_RENTAL("요가복대여"),
    SHOWER("샤워시설"),
    PARKING("주차"),
    WOMEN_ONLY("여성전용"),
    PRIVATE_LESSON("개인레슨");

    private String caption;

    public static Amenity fromDataString(String data) {
        return Arrays.stream(Amenity.values()).filter(a -> a.getCaption().equals(data)).findFirst().orElseThrow(() -> new NoSuchElementException("해당 명칭에 맞는 필터 없음"));
    }
}
