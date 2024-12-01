package com.iqkv.boot.starter.kafka;

import com.iqkv.boot.info.BuildInfoConfig;
import com.iqkv.boot.kafka.config.KafkaAdminProperties;
import com.iqkv.boot.kafka.config.KafkaErrorHandlingProperties;
import com.iqkv.boot.kafka.config.KafkaHealthIndicatorConfig;
import com.iqkv.boot.kafka.config.KafkaTopicDefinitionProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(value = {
    KafkaErrorHandlingProperties.class,
    KafkaTopicDefinitionProperties.class,
    KafkaAdminProperties.class
})
@Import({BuildInfoConfig.class, KafkaHealthIndicatorConfig.class})
public class KafkaAutoConfiguration {
}
