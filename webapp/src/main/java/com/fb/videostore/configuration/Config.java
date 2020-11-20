package com.fb.videostore.configuration;

import com.fb.videostore.service.MovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public MovieService movieService() {
        return new MovieService() {
            public void register(String serialNumber, String title) {

            }

            public void rent(String serialNumber, String customer) {

            }
        };
    }
}