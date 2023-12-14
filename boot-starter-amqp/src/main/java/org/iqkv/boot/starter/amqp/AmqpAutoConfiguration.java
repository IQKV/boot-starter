package org.iqkv.boot.starter.amqp;

import org.iqkv.boot.build.BuildInfoConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(BuildInfoConfig.class)
public class AmqpAutoConfiguration {
}
