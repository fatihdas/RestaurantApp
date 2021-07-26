package com.restaurantapp.restapp.entity;

import com.restaurantapp.restapp.enumerated.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID",nullable = false)
    private long id;

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "PASSWORD",nullable = false)
    private String password;

    @Column(name = "EMAİL",nullable = false)
    private String email;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Roles roles;

    @OneToMany(mappedBy = "user")
    private List<Restaurant> restaurants;


}
