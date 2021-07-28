package com.restaurantapp.restapp.model;

import com.restaurantapp.restapp.model.basemodel.BaseModel;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@Entity
@Table(name = "items")
public class Item extends BaseModel {
    public Item(long id) {
        super(id);
    }

    public Item() {
        super();
    }

    public Item(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Item(long id, String name, float price) {
        super(id);
        this.name = name;
        this.price = price;
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

//    @ManyToMany(mappedBy = "itemList")
//    private List<Meal> mealList;
}
