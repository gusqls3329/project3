package com.team5.projrental.entities.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Address {

    private String addr;
    private String restAddr;
    private Double x;
    private Double y;

}