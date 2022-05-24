package org.ujar.boot.starter.logbook;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
    LogbookConfiguration.class
})
public class LogbookAutoConfiguration {
}
