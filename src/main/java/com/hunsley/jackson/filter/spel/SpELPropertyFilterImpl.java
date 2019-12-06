package com.hunsley.jackson.filter.spel;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;

public class SpELPropertyFilterImpl implements PropertyFilter {

  @Override
  public void serializeAsField(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, PropertyWriter propertyWriter) throws Exception {

  }

  @Override
  public void serializeAsElement(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider,
                                 PropertyWriter propertyWriter) throws Exception {

  }

  @Override
  public void depositSchemaProperty(PropertyWriter propertyWriter, ObjectNode objectNode, SerializerProvider serializerProvider) throws JsonMappingException {

  }

  @Override
  public void depositSchemaProperty(PropertyWriter propertyWriter, JsonObjectFormatVisitor jsonObjectFormatVisitor, SerializerProvider serializerProvider) throws JsonMappingException {

  }
}
