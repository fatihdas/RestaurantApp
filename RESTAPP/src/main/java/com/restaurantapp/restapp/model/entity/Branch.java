package com.restaurantapp.restapp.model.entity;

import com.restaurantapp.restapp.model.entity.enumerated.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "branches")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('WAITING','REJECTED','APPROVED')")
    private Status status;

    @OneToOne( fetch = FetchType.EAGER)
    private Menu menu;

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

}
