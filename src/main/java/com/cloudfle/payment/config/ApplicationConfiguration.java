package com.cloudfle.payment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {

  @Bean
  @Scope (ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public Logger logger(InjectionPoint injectionPoint) {
    Class<?> clazz = injectionPoint.getMember().getDeclaringClass();
    return LoggerFactory.getLogger(clazz);
  }
}
