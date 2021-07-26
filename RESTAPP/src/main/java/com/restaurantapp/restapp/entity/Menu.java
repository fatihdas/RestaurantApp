package com.restaurantapp.restapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MENU")
public class Menu {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Branch branch;

    @OneToMany
    private List<Meal> mealList;
}
