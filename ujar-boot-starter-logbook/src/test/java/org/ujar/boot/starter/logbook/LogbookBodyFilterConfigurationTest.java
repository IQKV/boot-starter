package org.ujar.boot.starter.logbook;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.zalando.logbook.autoconfigure.LogbookProperties;
import org.zalando.logbook.json.CompactingJsonBodyFilter;

class LogbookBodyFilterConfigurationTest {

  @Test
  void shouldCreateCompactingJsonBodyFilterIfMaxBodySizeNotConfigured() {
    // given
    var logbookBodyFilterConfig = new LogbookBodyFilterConfig();
    var logbookProperties = mock(LogbookProperties.class);
    var logbookWriteProperties = mock(LogbookProperties.Write.class);
    when(logbookProperties.getWrite()).thenReturn(logbookWriteProperties);
    when(logbookWriteProperties.getMaxBodySize()).thenReturn(-1);

    // when
    var bodyFilter = logbookBodyFilterConfig.bodyFilter(logbookProperties);

    // then
    assertTrue(bodyFilter instanceof CompactingJsonBodyFilter);
  }

  @Test
  void shouldMergeCompactingJsonBodyFilterWithTruncateIfMaxBodySizeConfigured() {
    // given
    var logbookBodyFilterConfig = new LogbookBodyFilterConfig();
    var logbookProperties = mock(LogbookProperties.class);
    var logbookWriteProperties = mock(LogbookProperties.Write.class);
    when(logbookProperties.getWrite()).thenReturn(logbookWriteProperties);
    when(logbookWriteProperties.getMaxBodySize()).thenReturn(1000);

    // when
    var bodyFilter = logbookBodyFilterConfig.bodyFilter(logbookProperties);

    // then
    assertFalse(bodyFilter instanceof CompactingJsonBodyFilter);
  }
}
