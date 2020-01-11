package net.fittable.persistence.converters;

import net.fittable.domain.business.SocialAddress;

import javax.persistence.AttributeConverter;

public class SocialAddressConverter implements AttributeConverter<SocialAddress, String> {
    @Override
    public String convertToDatabaseColumn(SocialAddress socialAddress) {
        return socialAddress.concatAddresses();
    }

    @Override
    public SocialAddress convertToEntityAttribute(String s) {
        return SocialAddress.fromConcatenatedString(s);
    }
}
