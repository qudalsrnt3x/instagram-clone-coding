package com.hanghae.instagramclonecoding.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedOrigin("http://**");
        config.addAllowedHeader("*");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        config.addAllowedMethod("OPTION");



        source.registerCorsConfiguration("/user/**", config);
        source.registerCorsConfiguration("/api/**", config);
        source.registerCorsConfiguration("/like/**", config);
        source.registerCorsConfiguration("/comment/**", config);
        return new CorsFilter(source);

//        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
//        corsConfiguration.setAllowedMethods(Arrays.asList("PUT, GET, DELETE, POST, OPTION, PATCH, HEAD"));
//        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
//        corsConfiguration.setMaxAge(3600L);
//        corsConfiguration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);




    }
}
