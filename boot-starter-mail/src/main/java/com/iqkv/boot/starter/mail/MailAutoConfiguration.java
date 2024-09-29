package com.iqkv.boot.starter.mail;

import com.iqkv.boot.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {
    MailProperties.class
})
public class MailAutoConfiguration {
}
