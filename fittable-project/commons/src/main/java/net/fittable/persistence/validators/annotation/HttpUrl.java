package net.fittable.persistence.validators.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpUrl {
    String message() default "URL 형식의 문자열만 입력할 수 있습니다.";
}
