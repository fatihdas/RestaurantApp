package com.restaurantapp.restapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEAL")
public class Meal {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Menu menu;

    @ManyToMany
    private List<Item> itemList;

    private String name;

    private float price;


}
