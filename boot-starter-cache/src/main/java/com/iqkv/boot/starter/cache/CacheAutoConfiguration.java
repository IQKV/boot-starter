package com.iqkv.boot.starter.cache;

import com.iqkv.boot.info.BuildInfoConfig;
import com.iqkv.boot.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BuildInfoConfig.class)
@EnableConfigurationProperties(CacheProperties.class)
public class CacheAutoConfiguration {

}
