package org.iqkv.boot.starter.kafka;

import org.iqkv.boot.build.BuildInfoConfig;
import org.iqkv.boot.kafka.config.KafkaAdminProperties;
import org.iqkv.boot.kafka.config.KafkaErrorHandlingProperties;
import org.iqkv.boot.kafka.config.KafkaHealthIndicatorConfig;
import org.iqkv.boot.kafka.config.KafkaTopicDefinitionProperties;
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
