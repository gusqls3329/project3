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
    @Column(name = "ipayment")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "istock")
    private Stock stock;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "iproduct")
    private Product product;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "iusers")
    private Users users;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ipayment_record")
    private PaymentRecord paymentRecord;

    @Embedded
    private RentalDates rentalDates;

    private Integer duration;

    private Integer totalPrice;
    private Integer deposit;
    private String code;
}
