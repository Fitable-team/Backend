package net.fittable.domain.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudioImageList {
    private static final String IMAGE_DIR_DELIMITER = ";";

    private String representativeImage;
    private List<String> imageDirectories;

    public String concatDirectories() {
        if(StringUtils.isEmpty(this.representativeImage)) {
            return "";
        }

        return this.representativeImage
                .concat(IMAGE_DIR_DELIMITER)
                .concat(String.join(IMAGE_DIR_DELIMITER, imageDirectories));
    }

    public static StudioImageList fromConcatenatedString(String concatString) {
        if(StringUtils.isEmpty(concatString)) {
            return new StudioImageList();
        }

        String[] splittedDirectories = concatString.split(IMAGE_DIR_DELIMITER);
        String representativeImage = splittedDirectories[0];
        List<String> otherImages = new ArrayList<>();

        for(int i = 1; i < splittedDirectories.length; i++) {
            otherImages.add(splittedDirectories[i]);
        }

        return new StudioImageList(splittedDirectories[0], otherImages);
    }
}
