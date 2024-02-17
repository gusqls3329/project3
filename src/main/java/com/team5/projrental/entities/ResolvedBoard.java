package com.team5.projrental.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ResolvedBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iresolvedBoard;

    @ManyToOne
    @JoinColumn(name = "iadmin")
    private Admin admin;

    @OneToOne
    @JoinColumn(name = "iboard")
    private Board board;
}
