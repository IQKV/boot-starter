package org.ujar.boot.starter.restful;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.ujar.boot.starter.restful.swagger.SwaggerConfiguration;
import org.ujar.boot.starter.restful.web.WebConfig;

@Configuration
@Import(value = {
    WebConfig.class,
    SwaggerConfiguration.class
})
@EnableConfigurationProperties(ProgramBuildInfoProperties.class)
public class RestfulAutoConfiguration {
}
