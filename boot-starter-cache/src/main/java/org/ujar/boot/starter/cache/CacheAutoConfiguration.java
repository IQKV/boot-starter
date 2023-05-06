package org.ujar.boot.starter.cache;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.ujar.boot.build.BuildInfoConfig;
import org.ujar.boot.cache.CacheProperties;

@Configuration
@Import(BuildInfoConfig.class)
@EnableConfigurationProperties(CacheProperties.class)
public class CacheAutoConfiguration {

}
