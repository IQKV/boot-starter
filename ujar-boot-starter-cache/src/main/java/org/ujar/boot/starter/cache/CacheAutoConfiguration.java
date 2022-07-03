package org.ujar.boot.starter.cache;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CacheSectionProperties.class)
public class CacheAutoConfiguration {

}
