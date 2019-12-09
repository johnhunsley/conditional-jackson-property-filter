package com.hunsley.jackson.filter.spel;

import static com.hunsley.jackson.filter.spel.JacksonSpELFilterConfig.SPEL_FILTER_NAME;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.hunsley.jackson.filter.spel.filter.SpELPropertyFilterImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JacksonSpELFilterProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

    if(bean instanceof ObjectMapper) {
      ObjectMapper objectMapper = (ObjectMapper)bean;
      objectMapper.setFilterProvider(
          new SimpleFilterProvider().addFilter(SPEL_FILTER_NAME, new SpELPropertyFilterImpl()));
    }

    return bean;
  }
}
