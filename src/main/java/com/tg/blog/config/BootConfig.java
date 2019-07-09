package com.tg.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ConfigurationProperties
@PropertySources({
        @PropertySource("classpath:props/application-${user.env}.properties")
})
public class BootConfig {
}
