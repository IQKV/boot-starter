package org.ujar.boot.starter.amqp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.ujar.boot.build.BuildInfoConfig;

@Configuration
@Import(BuildInfoConfig.class)
public class AmqpAutoConfiguration {
}
