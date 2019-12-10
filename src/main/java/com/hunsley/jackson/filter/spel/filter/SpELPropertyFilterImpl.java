package com.hunsley.jackson.filter.spel.filter;

import static java.lang.String.format;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import java.lang.reflect.Field;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

/**
 * A {@link PropertyFilter} which evaluates SpEL expressions annotated on fields within the pojo defined as being
 * filtered. Annotate the pojo with the {@link com.fasterxml.jackson.annotation.JsonFilter} annotation and provide
 * the name as 'spel' e.g.
 *
 * @JsonFilter("spel")
 *
 * Then annotate each field you want to evaluate with a the {@link JsonFilterExpression} annotation and ensure the
 * expression evaluates as a boolean. In addition you can reference the field in the expression using the '?'
 * character rather than having to use #this.<fieldName>
 *
 * @author jhunsley
 */
@Slf4j
public final class SpELPropertyFilterImpl implements PropertyFilter {
  private static final String SPEL_SELF_REF_PREFIX = "#this";
  private static final String FIELD_REF = "\\?";

  private final ExpressionParser parser;

  public SpELPropertyFilterImpl() {
    parser = new SpelExpressionParser();
  }

  /**
   * Check to see if the field being evaluated by the filter is annotated with the {@link JsonFilterExpression}
   * if it is then load the object into the evaluation context, parse the expression and evaluate the field value.
   * If it evaluates as false it will be suppressed in the json output.
   */
  public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider,
                               PropertyWriter writer) throws Exception {
    Field field = obj.getClass().getDeclaredField(writer.getName());

    if (field.isAnnotationPresent(JsonFilterExpression.class)) {
      log.info(format("Filtering fields for json serialisation. Evaluating field: %s from instance of type: %s ",
          field.getName(), obj.getClass().getName()));
      JsonFilterExpression jsonFilterExpression = field.getAnnotation(JsonFilterExpression.class);
      Expression exp = parser.parseExpression(substituteFieldRef(jsonFilterExpression.value(), field));
      final StandardEvaluationContext context = new StandardEvaluationContext(obj);
      checkExpressionReturnType(exp, context);

      final Boolean value = exp.getValue(context, Boolean.class);

      if (value != null && !value) {
        log.info(format("field expression evaluates to false. field %s will not be included in the json output.",
            field.getName()));
        return;
      }
    }

    writer.serializeAsField(obj, jsonGenerator, serializerProvider);
  }

  /**
   * Ensure the expression evaluates as a boolean.
   * @throws SpelEvaluationException with SpelEvaluationException#TYPE_CONVERSION_ERROR if not.
   */
  private void checkExpressionReturnType(final Expression exp, final StandardEvaluationContext context) {
    final Class<?> type = exp.getValueType(context);

    if (type == null || !type.equals(Boolean.class)) {
      log.warn(format("expression: %s does not evaluate as a boolean. Cannot be used to evaluate its inclusivity",
          exp.getExpressionString()));
      throw new SpelEvaluationException(SpelMessage.TYPE_CONVERSION_ERROR);
    }
  }

  /**
   * replaces the self ref wildcard '?' with the ref to the variable by name - #this.<fieldName>
   */
  private String substituteFieldRef(final String expression, Field field) {
    assert !StringUtils.isEmpty(expression);
    return expression.replaceAll(FIELD_REF, SPEL_SELF_REF_PREFIX + "." + field.getName());
  }

  public void serializeAsElement(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider,
                                 PropertyWriter propertyWriter) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  public void depositSchemaProperty(PropertyWriter propertyWriter, ObjectNode objectNode,
                                    SerializerProvider serializerProvider) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  public void depositSchemaProperty(PropertyWriter propertyWriter, JsonObjectFormatVisitor jsonObjectFormatVisitor,
                                    SerializerProvider serializerProvider) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
}
