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
    @Column(name = "iresolved_board")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "iadmin")
    private Admin admin;

    @OneToOne
    @JoinColumn(name = "iboard")
    private Board board;
}
