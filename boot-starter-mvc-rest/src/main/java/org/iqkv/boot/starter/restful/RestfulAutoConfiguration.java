package org.iqkv.boot.starter.restful;

import org.iqkv.boot.web.rest.WebConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
    WebConfig.class
})
public class RestfulAutoConfiguration {
}
