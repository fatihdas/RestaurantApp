package com.restaurantapp.restapp;

import com.restaurantapp.restapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RestappApplication {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initUsers(){

    }

    public static void main(String[] args) {
        SpringApplication.run(RestappApplication.class, args);

    }

}
