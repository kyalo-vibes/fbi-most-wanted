package com.kyalo.fbimostwanted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
public class FbiMostWantedApplication {

    public static void main(String[] args) {
        SpringApplication.run(FbiMostWantedApplication.class, args);
    }

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

}
