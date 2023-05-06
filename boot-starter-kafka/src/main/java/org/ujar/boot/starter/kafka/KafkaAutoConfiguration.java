package org.ujar.boot.starter.kafka;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.ujar.boot.build.BuildInfoConfig;
import org.ujar.boot.kafka.config.KafkaAdminProperties;
import org.ujar.boot.kafka.config.KafkaErrorHandlingProperties;
import org.ujar.boot.kafka.config.KafkaHealthIndicatorConfig;
import org.ujar.boot.kafka.config.KafkaTopicDefinitionProperties;

@Configuration
@EnableConfigurationProperties(value = {
    KafkaErrorHandlingProperties.class,
    KafkaTopicDefinitionProperties.class,
    KafkaAdminProperties.class
})
@Import({BuildInfoConfig.class, KafkaHealthIndicatorConfig.class})
public class KafkaAutoConfiguration {
}
