package com.restaurantapp.restapp.model;

import com.restaurantapp.restapp.model.basemodel.BaseModel;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@Table(name = "baskets")
public class Basket extends BaseModel {
    public Basket() {
        super();
    }

    public Basket(User user, List<Meal> mealList, int count, float totalPrice) {
        this.user = user;
        this.mealList = mealList;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public Basket(long id, User user, List<Meal> mealList, int count, float totalPrice) {
        super(id);
        this.user = user;
        this.mealList = mealList;
        this.count = count;
        this.totalPrice = totalPrice;
    }

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

    public Basket(long id) {
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Meal> mealList;

    private int count;

    private float totalPrice;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Meal> getMealList() {
        return mealList;
    }

    public void setMealList(List<Meal> mealList) {
        this.mealList = mealList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
