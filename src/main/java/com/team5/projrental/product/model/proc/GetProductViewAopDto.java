package com.team5.projrental.product.model.proc;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductViewAopDto {
    private Long iproduct;
    private Integer beforeView;

    public GetProductViewAopDto(Long iproduct) {
        this.iproduct = iproduct;
    }
}
