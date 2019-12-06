package com.hunsley.jackson.filter.spel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.junit.Before;
import org.junit.Test;

public class SpELPropertyFilterImplTest {

  private ObjectMapper objectMapper;

  @Before
  public void init() {
    objectMapper = new ObjectMapper();
    objectMapper.setFilterProvider(
        new SimpleFilterProvider().addFilter(
            "conditional", new SpELPropertyFilterImpl()));
  }

  @Test
  public void testBasicEvaluation() {

  }
}
