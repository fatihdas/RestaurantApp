package com.restaurantapp.restapp.model;

import com.restaurantapp.restapp.model.basemodel.BaseModel;
import com.restaurantapp.restapp.model.enumerated.Status;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@Table(name = "branches")
public class Branch extends BaseModel {
    public Branch(long id) {
        super(id);
    }

    public Branch() {
        super();
    }

    public Branch(String name, Restaurant restaurant, Status status, List<Comment> comment, Menu menu, Address address) {
        this.name = name;
        this.restaurant = restaurant;
        this.status = status;
        this.comment = comment;
        this.menu = menu;
        this.address = address;
    }

    public Branch(long id, String name, Restaurant restaurant, Status status, List<Comment> comment, Menu menu, Address address) {
        super(id);
        this.name = name;
        this.restaurant = restaurant;
        this.status = status;
        this.comment = comment;
        this.menu = menu;
        this.address = address;
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comment;

    @OneToOne(mappedBy = "branch", fetch = FetchType.EAGER)
    private Menu menu;

    @OneToOne(mappedBy = "branch", fetch = FetchType.LAZY)
    private Address address;


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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
