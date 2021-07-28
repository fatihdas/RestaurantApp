package com.restaurantapp.restapp.model;

import com.restaurantapp.restapp.model.basemodel.BaseModel;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@Table(name = "meals")
public class Meal extends BaseModel {
    public Meal(long id) {
        super(id);
    }

    public Meal() {
        super();
    }

    public Meal(String name, float price, List<Item> itemList) {
        this.name = name;
        this.price = price;
        this.itemList = itemList;
    }

    public Meal(long id, String name, float price, List<Item> itemList) {
        super(id);
        this.name = name;
        this.price = price;
        this.itemList = itemList;
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "item_meal", joinColumns = @JoinColumn(name = "meal_id")
            , inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> itemList;


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
