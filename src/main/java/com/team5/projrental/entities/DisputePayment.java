package com.team5.projrental.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DisputePayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idispute_payment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idispute")
    private Dispute dispute;

    @ManyToOne
    @JoinColumn(name = "ipayment")
    private Payment payment;

}
