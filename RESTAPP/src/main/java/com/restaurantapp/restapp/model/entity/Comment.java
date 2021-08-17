package com.restaurantapp.restapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String content;

    @Column(name = "taste_point")
    private Integer tastePoint;

    @Column(name = "speed_point")
    private Integer speedPoint;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    private Branch branch;

}
