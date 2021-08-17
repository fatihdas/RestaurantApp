package com.restaurantapp.restapp.model.entity;

import com.restaurantapp.restapp.model.entity.enumerated.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "id"))
    @Column(columnDefinition = "enum('BUYER','SELLER','ADMIN')")
    @Enumerated(EnumType.STRING)
    private List<Roles> roles;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addressList = new ArrayList<>();

}
