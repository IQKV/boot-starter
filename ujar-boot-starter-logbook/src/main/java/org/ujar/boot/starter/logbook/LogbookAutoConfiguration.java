package org.ujar.boot.starter.logbook;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
    LogbookConfig.class
})
public class LogbookAutoConfiguration {
}
