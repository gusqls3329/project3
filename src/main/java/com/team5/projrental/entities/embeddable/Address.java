package com.team5.projrental.entities.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;


@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    private String addr;
    private String restAddr;
    private Double x;
    private Double y;



}
