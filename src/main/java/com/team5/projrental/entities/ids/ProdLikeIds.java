package com.team5.projrental.entities.ids;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class ProdLikeIds implements Serializable {
    private Long iproduct;
    private Long iusers;
}
