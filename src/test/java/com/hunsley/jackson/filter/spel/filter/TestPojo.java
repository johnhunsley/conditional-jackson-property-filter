package com.hunsley.jackson.filter.spel.filter;


import static com.hunsley.jackson.filter.spel.JacksonSpELFilterConfig.SPEL_FILTER_NAME;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;

@Data
@JsonFilter(SPEL_FILTER_NAME)
public class TestPojo {

  @JsonFilterExpression("? != null")
  private String myStr;

  @JsonFilterExpression("? > 0 && #this.myStr != null")
  private int myInt;
}
