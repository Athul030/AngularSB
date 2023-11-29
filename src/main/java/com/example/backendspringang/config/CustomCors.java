package com.example.backendspringang.config;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Component
public class CustomCors implements CorsConfigurationSource {
    @Override
    @Nullable
    public CorsConfiguration getCorsConfiguration(HttpServletRequest arg0) {
        CorsConfiguration config=new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setExposedHeaders(Arrays.asList("Authorization"));
        config.setMaxAge(3600L);

        return config;
    }
}
