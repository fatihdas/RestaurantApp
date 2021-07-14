package com.restaurantapp.restapp.model;

import com.restaurantapp.restapp.model.basemodel.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meals")
public class Meal extends BaseModel {
    public Meal(long id) {
        super(id);
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }

    private String name;

    private float price;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "menu_id")
//    private Menu menu;

//    @ManyToOne
//    @JoinColumn(name = "basket_id")
//    private Basket basket;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "item_meal",joinColumns = @JoinColumn(name = "meal_id")
    ,inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> itemList;




}
