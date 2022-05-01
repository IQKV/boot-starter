package org.ujar.starter.rest.logbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.ujar.starter.rest.logbook.LogbookStrategyProperties.CustomLogbookStrategy;
import org.zalando.logbook.BodyFilter;
import org.zalando.logbook.DefaultStrategy;
import org.zalando.logbook.Strategy;


class LogbookStrategyBeanPostProcessorTest {

  @Test
  void shouldSkipNonStrategyClasses() {
    // given
    var strategyProperties = mock(LogbookStrategyProperties.class);
    var strategyBeanPostProcessor = new LogbookStrategyBeanPostProcessor(0, strategyProperties);
    var bodyFilter = mock(BodyFilter.class);
    // when
    var resultBean = strategyBeanPostProcessor.postProcessBeforeInitialization(bodyFilter, "testName");
    // then
    assertEquals(bodyFilter, resultBean);
  }

  @Test
  void shouldCreateResponseOnStatusAtLeastStrategy() {
    // given
    var strategyProperties = mock(LogbookStrategyProperties.class);
    when(strategyProperties.getCustomStrategy()).thenReturn(CustomLogbookStrategy.RESPONSE_ON_STATUS_AT_LEAST);
    var strategyBeanPostProcessor = new LogbookStrategyBeanPostProcessor(0, strategyProperties);
    var defaultStrategy = new DefaultStrategy();
    // when
    var resultStrategy = strategyBeanPostProcessor.postProcessBeforeInitialization(defaultStrategy, "strategy");
    // then
    assertNotEquals(defaultStrategy, resultStrategy);
    assertTrue(resultStrategy instanceof ResponseOnStatusAtLeastStrategy);
  }

  @Test
  void shouldCreateWithoutResponseBodyStrategy() {
    // given
    var strategyProperties = mock(LogbookStrategyProperties.class);
    when(strategyProperties.getCustomStrategy()).thenReturn(CustomLogbookStrategy.WITHOUT_RESPONSE_BODY);
    var strategyBeanPostProcessor = new LogbookStrategyBeanPostProcessor(0, strategyProperties);
    var defaultStrategy = mock(Strategy.class);
    // when
    var resultStrategy = strategyBeanPostProcessor.postProcessBeforeInitialization(defaultStrategy, "strategy");
    // then
    assertTrue(resultStrategy instanceof WithoutResponseBodyStrategy);
  }
}
