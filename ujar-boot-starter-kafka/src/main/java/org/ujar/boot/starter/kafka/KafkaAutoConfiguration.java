package org.ujar.boot.starter.kafka;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.ujar.boot.starter.kafka.config.KafkaErrorHandlingProperties;
import org.ujar.boot.starter.kafka.config.KafkaHealthIndicatorConfig;
import org.ujar.boot.starter.kafka.config.KafkaHealthIndicatorProperties;
import org.ujar.boot.starter.kafka.config.KafkaStreamsHealthIndicatorConfig;
import org.ujar.boot.starter.kafka.config.KafkaTopicDefinitionProperties;

@Configuration
@Import(value = {
    KafkaHealthIndicatorConfig.class,
    KafkaStreamsHealthIndicatorConfig.class
})
@EnableConfigurationProperties({
    KafkaHealthIndicatorProperties.class,
    KafkaErrorHandlingProperties.class,
    KafkaTopicDefinitionProperties.class
})
public class KafkaAutoConfiguration {
}
