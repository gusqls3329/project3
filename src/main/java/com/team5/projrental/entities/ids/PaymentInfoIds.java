package com.team5.projrental.entities.ids;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class PaymentInfoIds implements Serializable {

    private Long ipayment;
    private Long iusers;



}
