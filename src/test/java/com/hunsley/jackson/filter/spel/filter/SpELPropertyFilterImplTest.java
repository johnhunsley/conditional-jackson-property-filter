package com.hunsley.jackson.filter.spel.filter;

import static com.hunsley.jackson.filter.spel.JacksonSpELFilterConfig.SPEL_FILTER_NAME;
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
            SPEL_FILTER_NAME, new SpELPropertyFilterImpl()));
  }

  @Test
  public void testSefRefEvaluation() throws JsonProcessingException {
    TestPojo pojo = new TestPojo();
    pojo.setMyStr("bar");
    String json = objectMapper.writeValueAsString(pojo);
    assertFalse(json.contains("myInt"));
    assertTrue(json.contains("myStr"));
    System.out.println(json);

    pojo.setMyInt(1);
    pojo.setMyStr(null);
    json = objectMapper.writeValueAsString(pojo);
    assertFalse(json.contains("myInt"));
    assertFalse(json.contains("myStr"));
    System.out.println(json);

    pojo.setMyStr("foo");
    json = objectMapper.writeValueAsString(pojo);
    assertTrue(json.contains("myInt"));
    assertTrue(json.contains("myStr"));
    System.out.println(json);
  }
}
