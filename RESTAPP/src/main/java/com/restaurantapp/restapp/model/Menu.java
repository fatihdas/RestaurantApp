package com.restaurantapp.restapp.model;

import com.restaurantapp.restapp.model.basemodel.BaseModel;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@Table(name = "menus")
public class Menu extends BaseModel {
    public Menu() {
        super();
    }

    public Menu(Branch branch, List<Meal> mealList) {
        this.branch = branch;
        this.mealList = mealList;
    }

    public Menu(long id, Branch branch, List<Meal> mealList) {
        super(id);
        this.branch = branch;
        this.mealList = mealList;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<Meal> getMealList() {
        return mealList;
    }

    public void setMealList(List<Meal> mealList) {
        this.mealList = mealList;
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

    public Menu(long id) {
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Meal> mealList;
}
