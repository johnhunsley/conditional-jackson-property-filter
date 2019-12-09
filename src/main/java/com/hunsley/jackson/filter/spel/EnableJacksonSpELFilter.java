package com.hunsley.jackson.filter.spel;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Import({})
@JacksonAnnotation
public @interface EnableJacksonSpELFilter {
}
