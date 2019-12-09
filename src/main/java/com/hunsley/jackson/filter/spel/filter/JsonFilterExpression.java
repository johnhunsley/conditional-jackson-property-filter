package com.hunsley.jackson.filter.spel.filter;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Tells jackson to only include the annotated field in the json if the expression evaluates true.
 *
 * @author jhunsley
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonFilterExpression {

  /**
   * A SpEL expression
   */
  String value();
}
