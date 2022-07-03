package org.ujar.boot.starter.restful.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DefaultRestfulErrorHandler.class)
public class WebConfig {
}
