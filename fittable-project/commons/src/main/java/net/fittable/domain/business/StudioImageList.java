package net.fittable.domain.business;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class StudioImageList {

    private final List<String> imageDirectories;

    public String concatDirectories() {
        return String.join(";", imageDirectories);
    }

    public static StudioImageList fromConcatenatedString(String concatString) {
        return new StudioImageList(Arrays.asList(concatString.split(";")));
    }
}
