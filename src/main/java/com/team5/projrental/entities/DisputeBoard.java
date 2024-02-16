package com.team5.projrental.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DisputeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ipaymentBoard;

    @ManyToOne
    @JoinColumn(name = "idispute")
    private Dispute dispute;

    @ManyToOne
    @JoinColumn(name = "iboard")
    private Board board;


}
