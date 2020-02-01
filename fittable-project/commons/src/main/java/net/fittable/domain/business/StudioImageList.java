package net.fittable.domain.business;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class StudioImageList {
    private static final String IMAGE_DIR_DELIMITER = ";";

    private final String representativeImage;
    private final List<String> imageDirectories;

    public String concatDirectories() {
        return this.representativeImage
                .concat(IMAGE_DIR_DELIMITER)
                .concat(String.join(IMAGE_DIR_DELIMITER, imageDirectories));
    }

    public static StudioImageList fromConcatenatedString(String concatString) {
        String[] splittedDirectories = concatString.split(IMAGE_DIR_DELIMITER);
        String representativeImage = splittedDirectories[0];
        List<String> otherImages = new ArrayList<>();

        for(int i = 1; i < splittedDirectories.length; i++) {
            otherImages.add(splittedDirectories[i]);
        }

        return new StudioImageList(splittedDirectories[0], otherImages);
    }
}
