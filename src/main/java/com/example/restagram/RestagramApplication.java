package com.example.restagram;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RestagramApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestagramApplication.class, args);
    }

}
