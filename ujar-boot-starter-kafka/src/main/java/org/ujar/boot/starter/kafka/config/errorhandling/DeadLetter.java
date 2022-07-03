package org.ujar.boot.starter.kafka.config.errorhandling;

import java.time.Duration;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

public record DeadLetter(
    @NotNull Duration retention,
    @Nullable String suffix) {
}
