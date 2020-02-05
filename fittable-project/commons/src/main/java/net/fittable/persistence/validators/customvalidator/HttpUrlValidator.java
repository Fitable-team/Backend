package net.fittable.persistence.validators.customvalidator;

import net.fittable.persistence.validators.annotation.HttpUrl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class HttpUrlValidator implements ConstraintValidator<HttpUrl, String> {

    private static final Pattern HTTP_URL_MATCH_PATTERN = Pattern.compile("^(http|https):\\/\\/");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return HTTP_URL_MATCH_PATTERN.matcher(s).matches();
    }
}
