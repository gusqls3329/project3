package com.team5.projrental.entities;

import com.team5.projrental.entities.enums.PaymentDetailCategory;
import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.entities.mappedsuper.CreatedAt;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PaymentDetail extends CreatedAt {

    @Id
    @Column(name = "ipayment_detail")
    private String id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "iusers")
    private Users users;

    private String tid;

    @Enumerated(EnumType.STRING)
    private PaymentDetailCategory category;


}
