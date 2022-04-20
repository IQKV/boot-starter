package org.ujar.starter.rest;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.ujar.starter.rest.logbook.LogbookConfiguration;
import org.ujar.starter.rest.web.WebConfiguration;

@Configuration
@Import(value = {
    LogbookConfiguration.class,
    SwaggerConfiguration.class,
    PrometheusConfiguration.class,
    WebConfiguration.class,
})
@EnableConfigurationProperties({ProjectInfoProperties.class})
public class BaseAutoConfiguration {
}
