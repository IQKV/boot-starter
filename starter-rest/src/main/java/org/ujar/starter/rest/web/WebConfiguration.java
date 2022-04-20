package org.ujar.starter.rest.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DefaultRestErrorHandler.class)
public class WebConfiguration {
}
