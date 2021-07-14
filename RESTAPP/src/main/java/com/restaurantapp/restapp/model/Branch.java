package com.restaurantapp.restapp.model;

import com.restaurantapp.restapp.model.basemodel.BaseModel;
import com.restaurantapp.restapp.model.enumerated.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "branches")
@NoArgsConstructor
@AllArgsConstructor
public class Branch extends BaseModel {
    public Branch(long id) {
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

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Enumerated
    private Status status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comment;

    @OneToOne(mappedBy = "branch", fetch = FetchType.EAGER)
    private Menu menu;

    @OneToOne(mappedBy = "branch", fetch = FetchType.LAZY)
    private Address address;


}
