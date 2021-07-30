package com.restaurantapp.restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RestappApplication {

    @PostConstruct
    public void initUsers() {

    }

    public static void main(String[] args) {
        SpringApplication.run(RestappApplication.class, args);

    }

}
