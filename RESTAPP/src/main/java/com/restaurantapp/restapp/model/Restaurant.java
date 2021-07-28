package com.restaurantapp.restapp.model;

import com.restaurantapp.restapp.model.basemodel.BaseModel;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Builder
@Entity
@Table(name = "restaurants")
public class Restaurant extends BaseModel {
    public Restaurant(long id) {
        super(id);
    }

    public Restaurant() {
        super();
    }

    public Restaurant(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    public Restaurant(long id, String name, User owner) {
        super(id);
        this.name = name;
        this.owner = owner;
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

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
