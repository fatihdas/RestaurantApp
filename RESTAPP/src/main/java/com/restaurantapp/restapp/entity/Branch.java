package com.restaurantapp.restapp.entity;

import com.restaurantapp.restapp.enumerated.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "BRANCH")
@NoArgsConstructor
@AllArgsConstructor
public class Branch {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "restaurant_id",referencedColumnName = "id")
    private Restaurant restaurant;

    @Enumerated
    private Status status = Status.WAITING;

    @OneToMany
    private List<Comment> comment;

    @OneToOne(mappedBy = "branch")
    private Menu menu;


}
