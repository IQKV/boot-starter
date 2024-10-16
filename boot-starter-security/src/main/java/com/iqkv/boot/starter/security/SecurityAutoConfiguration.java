package com.iqkv.boot.starter.security;

import com.iqkv.boot.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {
    SecurityProperties.class
})
public class SecurityAutoConfiguration {
}
