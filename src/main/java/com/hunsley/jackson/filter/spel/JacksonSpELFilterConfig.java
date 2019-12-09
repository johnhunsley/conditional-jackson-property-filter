package com.hunsley.jackson.filter.spel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class JacksonSpELFilterConfig {
  public final static String SPEL_FILTER_NAME = "spel";

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
