package com.kazakov.eventkeeper.mainservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.kazakov.eventkeeper.statsclient.StatsClient;

@Configuration
public class ApplicationConfig {
    @Value("${statsService.url}")
    private String statsServiceUrl;

    @Bean
    public StatsClient statsClient(RestTemplateBuilder builder) {
        return new StatsClient(statsServiceUrl, builder);
    }
}
