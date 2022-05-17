package org.ujar.starter.rest.logbook;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.ujar.starter.rest.logbook.LogbookStrategyProperties.CustomLogbookStrategy;
import org.zalando.logbook.Strategy;

@SuppressFBWarnings("EI_EXPOSE_REP2")
public class LogbookStrategyBeanPostProcessor implements BeanPostProcessor {
  private final int minimumStatus;
  private final LogbookStrategyProperties logbookStrategyProperties;

  public LogbookStrategyBeanPostProcessor(int minimumStatus,
                                          LogbookStrategyProperties logbookStrategyProperties) {
    this.minimumStatus = minimumStatus;
    this.logbookStrategyProperties = logbookStrategyProperties;
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) {
    if (bean instanceof Strategy) {
      var customStrategy = logbookStrategyProperties.getCustomStrategy();
      if (customStrategy == CustomLogbookStrategy.RESPONSE_ON_STATUS_AT_LEAST) {
        return new ResponseOnStatusAtLeastStrategy(minimumStatus);
      } else if (customStrategy == CustomLogbookStrategy.WITHOUT_RESPONSE_BODY) {
        return new WithoutResponseBodyStrategy(minimumStatus);
      }
    }
    return bean;
  }
}
