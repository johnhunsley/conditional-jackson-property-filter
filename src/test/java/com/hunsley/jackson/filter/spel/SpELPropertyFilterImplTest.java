package com.hunsley.jackson.filter.spel;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
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
  public void testBasicEvaluation() throws JsonProcessingException {
    TestPojo pojo = new TestPojo();
    pojo.setMyInt(1);
    String json = objectMapper.writeValueAsString(pojo);
    assertTrue(json.contains("myInt"));

    pojo.setMyInt(0);
    json = objectMapper.writeValueAsString(pojo);
    assertFalse(json.contains("myInt"));
  }
}
