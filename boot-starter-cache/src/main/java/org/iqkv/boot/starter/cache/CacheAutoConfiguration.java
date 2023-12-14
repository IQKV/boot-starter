package org.iqkv.boot.starter.cache;

import org.iqkv.boot.build.BuildInfoConfig;
import org.iqkv.boot.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BuildInfoConfig.class)
@EnableConfigurationProperties(CacheProperties.class)
public class CacheAutoConfiguration {

}
