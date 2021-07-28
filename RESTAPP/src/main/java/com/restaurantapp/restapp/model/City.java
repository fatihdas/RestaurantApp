package com.restaurantapp.restapp.model;

import com.restaurantapp.restapp.model.basemodel.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "cities")
public class City extends BaseModel {
    public City(long id) {
        super(id);
    }

    public City() {
        super();
    }

    public City(String name) {
        this.name = name;
    }

    public City(long id, String name) {
        super(id);
        this.name = name;
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
}
