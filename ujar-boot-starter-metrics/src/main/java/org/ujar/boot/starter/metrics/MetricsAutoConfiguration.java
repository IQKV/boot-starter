package org.ujar.boot.starter.metrics;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
    PrometheusConfig.class,
})
public class MetricsAutoConfiguration {
}
