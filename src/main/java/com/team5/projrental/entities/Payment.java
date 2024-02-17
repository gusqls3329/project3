package com.team5.projrental.entities;

import com.team5.projrental.entities.embeddable.RentalDates;
import com.team5.projrental.entities.enums.PaymentMethod;
import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.entities.mappedsuper.CreatedAt;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payment extends CreatedAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ipayment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "istock")
    private Stock stock;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "iproduct")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "iusers")
    private Users users;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Embedded
    private RentalDates rentalDates;

    private Integer duration;

    private Integer totalPrice;
    private Integer deposit;
    private String code;
}
