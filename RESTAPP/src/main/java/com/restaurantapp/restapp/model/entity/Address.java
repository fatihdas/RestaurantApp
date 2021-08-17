package com.restaurantapp.restapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String cityName;

    @Column
    private String countyName;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User user;


}
