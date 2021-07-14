package com.restaurantapp.restapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RESTAURANT")
public class Restaurant {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ManyToOne
    private  User user;

}
