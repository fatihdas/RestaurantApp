package com.restaurantapp.restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RestappApplication {

    @PostConstruct
    public void initUsers() {

    }

    public static void main(String[] args) {
        SpringApplication.run(RestappApplication.class, args);

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
