package org.ujar.boot.starter.metrics;

import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean({ProcessorMetrics.class, PrometheusMeterRegistry.class})
public class PrometheusConfig {
  @Autowired
  protected void register(ProcessorMetrics processorMetrics, PrometheusMeterRegistry registry) {
    processorMetrics.bindTo(registry);
  }

  @Autowired
  protected void configurePercentiles(MetricsProperties metricsProperties) {
    if (metricsProperties == null) {
      return;
    }
    final var percentiles = metricsProperties.getDistribution().getPercentiles();
    if (!percentiles.containsKey("http.server.requests")) {
      percentiles.put("http.server.requests", new double[] {0.75, 0.95, 0.99});
    }
  }

}
