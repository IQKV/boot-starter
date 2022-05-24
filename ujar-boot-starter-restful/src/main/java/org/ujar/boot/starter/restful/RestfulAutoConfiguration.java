package org.ujar.boot.starter.restful;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.ujar.boot.starter.restful.web.WebConfiguration;

@Configuration
@Import(value = {
    PrometheusConfiguration.class,
    WebConfiguration.class,
})
public class RestfulAutoConfiguration {
}
