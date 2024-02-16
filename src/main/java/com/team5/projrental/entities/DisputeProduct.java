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
    private Long idisputeProduct;

    @ManyToOne
    @JoinColumn(name = "idispute")
    private Dispute dispute;

    @ManyToOne
    @JoinColumn(name = "iproduct")
    private Product product;

}
