package org.ujar.boot.starter.kafka.config;

import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.ThreadMetadata;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;

@Configuration
@ConditionalOnEnabledHealthIndicator("kstreams")
@ConditionalOnBean(StreamsBuilderFactoryBean.class)
@AutoConfigureAfter(KafkaStreamsDefaultConfiguration.class)
@RequiredArgsConstructor
public class KafkaStreamsHealthIndicatorConfig {

  private final StreamsBuilderFactoryBean streamsBuilderFactoryBean;

  @Bean
  public HealthIndicator kafkaStreamsHealthIndicator() {
    return () -> {
      final var kafkaStreams = streamsBuilderFactoryBean.getKafkaStreams();
      assert kafkaStreams != null;
      final var kafkaStreamsState = kafkaStreams.state();
      if (kafkaStreamsState == KafkaStreams.State.CREATED || kafkaStreamsState.isRunningOrRebalancing()) {
        return Health.up()
            .withDetail("state", kafkaStreamsState.name())
            .withDetail("threadStatus", buildThreadsStatus(kafkaStreams))
            .build();
      }

      return Health.down()
          .withDetail("state", kafkaStreamsState.name())
          .withDetail("threadStatus", buildThreadsStatus(kafkaStreams))
          .build();
    };
  }

  public Map<String, String> buildThreadsStatus(KafkaStreams kafkaStreams) {
    try {
      return kafkaStreams.metadataForLocalThreads().stream()
          .collect(Collectors.toMap(ThreadMetadata::threadName, ThreadMetadata::threadState));
    } catch (IllegalStateException e) {
      return Map.of("error", e.getMessage());
    }
  }
}
