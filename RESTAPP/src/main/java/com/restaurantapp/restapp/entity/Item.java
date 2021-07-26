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
@Table(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private float price;

    @ManyToMany(mappedBy = "itemList")
    private List<Meal> mealList;
}
