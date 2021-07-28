package com.restaurantapp.restapp.model;

import com.restaurantapp.restapp.model.basemodel.BaseModel;
import com.restaurantapp.restapp.model.enumerated.Roles;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@Table(name = "users")
public class User extends BaseModel {
    public User(long id) {
        super(id);
    }

    public User() {
        super();
    }

    public User(String name, String password, String email, Roles roles, List<Restaurant> restaurants, List<Address> addressList, List<Comment> commentList) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.restaurants = restaurants;
        this.addressList = addressList;
        this.commentList = commentList;
    }

    public User(long id, String name, String password, String email, Roles roles, List<Restaurant> restaurants, List<Address> addressList, List<Comment> commentList) {
        super(id);
        this.name = name;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.restaurants = restaurants;
        this.addressList = addressList;
        this.commentList = commentList;
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAİL", nullable = false)
    private String email;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Roles roles;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Restaurant> restaurants;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> addressList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> commentList;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
