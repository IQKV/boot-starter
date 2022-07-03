package org.ujar.boot.starter.logbook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;

@Configuration
@ConditionalOnClass(Logbook.class)
@EnableConfigurationProperties(LogbookStrategyProperties.class)
public class LogbookConfig {
  @Bean
  @ConditionalOnProperty(name = "logbook.custom-strategy")
  protected LogbookStrategyBeanPostProcessor logbookStrategyBeanPostProcessor(
      @Value("${logbook.minimum-status:0}") int minimumStatus,
      LogbookStrategyProperties logbookStrategyProperties) {
    return new LogbookStrategyBeanPostProcessor(minimumStatus, logbookStrategyProperties);
  }
}
