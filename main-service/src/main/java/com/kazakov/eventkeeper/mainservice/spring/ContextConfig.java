package com.kazakov.eventkeeper.mainservice.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.kazakov.eventkeeper.statsclient.StatsClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ContextConfig {
    @Value("${statsService.url}")
    private String statsServiceUrl;

    @Bean
    public StatsClient statsClient(RestTemplateBuilder builder) {
        return new StatsClient(statsServiceUrl, builder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}