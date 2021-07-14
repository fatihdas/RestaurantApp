package com.restaurantapp.restapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Address")
@NoArgsConstructor
@AllArgsConstructor
public class Addresses extends BaseDTO{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "id")
    private City city;

    @OneToOne
    @JoinColumn(name = "county_id",referencedColumnName = "id")
    private County county;

    @OneToOne
    @JoinColumn(name = "branch_id",referencedColumnName = "id")
    private Branch branch;

    @Column(name = "content",nullable = false)
    private String content;
}
