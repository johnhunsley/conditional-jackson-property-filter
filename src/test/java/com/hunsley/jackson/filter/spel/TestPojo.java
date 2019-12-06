package com.hunsley.jackson.filter.spel;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;

@Data
@JsonFilter("conditional")
public class TestPojo {

  private String myStr;

  @JsonFilterExpression("? > 0")
  private int myInt;
}
