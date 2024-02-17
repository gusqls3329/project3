package com.team5.projrental.entities;

import com.team5.projrental.entities.enums.PaymentInfoStatus;
import com.team5.projrental.entities.ids.PaymentInfoIds;
import com.team5.projrental.entities.inheritance.Users;
import com.team5.projrental.entities.mappedsuper.UpdatedAt;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PaymentInfo extends UpdatedAt {

    @EmbeddedId
    private PaymentInfoIds paymentInfoIds;

    //

    @MapsId("ipayment")
    @ManyToOne
    @JoinColumn(name = "ipayment")
    private Payment payment;

    @MapsId("iusers")
    @ManyToOne
    @JoinColumn(name = "iusers")
    private Users users;

    @Enumerated(EnumType.STRING)
    private PaymentInfoStatus status;
    private String code;


}
