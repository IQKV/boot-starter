/*
 * Copyright 2025 IQKV Team, and the original author or authors from the JHipster project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
