package com.java.sdpprojectay2.config;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapConfig {

    @Value("${google.maps.api.key}")
    private String apiKey;

    @Bean
    public GeoApiContext geoApiContext() {
        return new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    public String getApiKey() {
        return apiKey;
    }

    public MapConfig setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }
}
