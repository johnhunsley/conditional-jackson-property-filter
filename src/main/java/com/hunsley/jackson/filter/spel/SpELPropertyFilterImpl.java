package com.hunsley.jackson.filter.spel;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import java.lang.reflect.Field;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELPropertyFilterImpl implements PropertyFilter {
  private final ExpressionParser parser;

  public SpELPropertyFilterImpl() {
    parser = new SpelExpressionParser();
  }

  public SpELPropertyFilterImpl(ExpressionParser parser) {
    this.parser = parser;
  }

  /**
   *
   * @param obj
   * @param jsonGenerator
   * @param serializerProvider
   * @param writer
   * @throws Exception
   */
  public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider,
                               PropertyWriter writer) throws Exception {
    Field field = obj.getClass().getDeclaredField(writer.getName());

    if(field.isAnnotationPresent(JsonFilterExpression.class)) {
      JsonFilterExpression jsonFilterExpression = field.getAnnotation(JsonFilterExpression.class);
      Expression exp = parser.parseExpression(jsonFilterExpression.value());
      StandardEvaluationContext  context = new StandardEvaluationContext();
      context.setRootObject(obj);

      if(!exp.getValueType(context).equals(Boolean.class)) {
        throw new SpelEvaluationException(SpelMessage.TYPE_CONVERSION_ERROR);
      }

      if(exp.getValue(context, Boolean.class)) {
        writer.serializeAsField(obj, jsonGenerator, serializerProvider);
      }
    }
  }

  public void serializeAsElement(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider,
                                 PropertyWriter propertyWriter) throws Exception {
    throw new UnsupportedOperationException();
  }

  public void depositSchemaProperty(PropertyWriter propertyWriter, ObjectNode objectNode,
                                    SerializerProvider serializerProvider) throws JsonMappingException {
    throw new UnsupportedOperationException();
  }

  public void depositSchemaProperty(PropertyWriter propertyWriter, JsonObjectFormatVisitor jsonObjectFormatVisitor,
                                    SerializerProvider serializerProvider) throws JsonMappingException {
    throw new UnsupportedOperationException();
  }
}
