package org.ujar.boot.starter.kafka.config;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;
import org.ujar.boot.starter.kafka.config.errorhandling.Backoff;
import org.ujar.boot.starter.kafka.config.errorhandling.DeadLetter;

@ConstructorBinding
@ConfigurationProperties(prefix = "ujar.kafka.error-handling")
@Validated
public record KafkaErrorHandlingProperties(
    @NotNull @Valid DeadLetter deadLetter,
    @NotNull @Valid Backoff backoff) {
}

