package com.team5.projrental.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DisputeProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idispute_product")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idispute")
    private Dispute dispute;

    @ManyToOne
    @JoinColumn(name = "iproduct")
    private Product product;

}
