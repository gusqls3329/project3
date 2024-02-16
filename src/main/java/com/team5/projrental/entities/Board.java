package com.team5.projrental.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long iboard;

    //@ManyToOne
    //@JoinColumn(name = "iuser", nullable = false)
    //private User user;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private Long view;
}
