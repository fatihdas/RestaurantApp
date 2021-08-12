package com.restaurantapp.restapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@Table(name = "menus")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "meal_menu",joinColumns = @JoinColumn(name = "meal_id")
    ,inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private List<Meal> mealList;

    @Column
    private String name;
}
