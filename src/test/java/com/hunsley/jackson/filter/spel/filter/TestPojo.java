package com.hunsley.jackson.filter.spel.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;

@Data
@JsonFilter("spel")
public class TestPojo {

  private String myStr;

  @JsonFilterExpression("#this.myInt > 0")
  private int myInt;
}
