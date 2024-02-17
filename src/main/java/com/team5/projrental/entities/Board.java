package com.team5.projrental.entities;


import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.entities.mappedsuper.BaseAt;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Board extends BaseAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long iboard;

    @ManyToOne
    @JoinColumn(name = "iusers", nullable = false)
    private Users users;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long view;
}
