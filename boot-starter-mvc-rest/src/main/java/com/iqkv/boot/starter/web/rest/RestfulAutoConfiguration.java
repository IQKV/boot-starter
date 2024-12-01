package com.iqkv.boot.starter.web.rest;

import com.iqkv.boot.mvc.rest.WebConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
    WebConfig.class
})
public class RestfulAutoConfiguration {
}
