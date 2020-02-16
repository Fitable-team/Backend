package net.fittable.persistence.converters;

import net.fittable.domain.business.StudioImageList;

import javax.persistence.AttributeConverter;

public class StudioImageListConverter implements AttributeConverter<StudioImageList, String> {
    @Override
    public String convertToDatabaseColumn(StudioImageList studioImageList) {
        if(studioImageList == null) {
            return "";
        }

        return studioImageList.concatDirectories();
    }

    @Override
    public StudioImageList convertToEntityAttribute(String s) {
        return StudioImageList.fromConcatenatedString(s);
    }
}
