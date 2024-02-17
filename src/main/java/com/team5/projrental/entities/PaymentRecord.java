package com.team5.projrental.entities;

import com.team5.projrental.entities.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PaymentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ipaymentRecord;

    private String tid;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
}
