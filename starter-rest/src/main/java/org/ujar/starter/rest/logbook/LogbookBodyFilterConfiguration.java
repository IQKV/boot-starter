package org.ujar.starter.rest.logbook;

import static org.zalando.logbook.BodyFilters.truncate;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.BodyFilter;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.autoconfigure.LogbookProperties;
import org.zalando.logbook.json.CompactingJsonBodyFilter;

@Configuration
@ConditionalOnClass(Logbook.class)
class LogbookBodyFilterConfiguration {

  @Bean
  protected BodyFilter bodyFilter(LogbookProperties properties) {
    final LogbookProperties.Write write = properties.getWrite();
    final int maxBodySize = write.getMaxBodySize();

    if (maxBodySize < 0) {
      return new CompactingJsonBodyFilter();
    }

    return BodyFilter.merge(new CompactingJsonBodyFilter(), truncate(maxBodySize));
  }
}
