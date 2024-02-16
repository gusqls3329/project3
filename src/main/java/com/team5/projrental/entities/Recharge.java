package com.team5.projrental.entities;

import com.team5.projrental.entities.enums.PaymentMethod;
import com.team5.projrental.entities.mappedsuper.CreatedAt;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Recharge extends CreatedAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long irecharge;

    @ManyToOne
    @JoinColumn(name = "iuser")
    private Comp comp;

    private PaymentMethod method;

    private Integer cash;


}
