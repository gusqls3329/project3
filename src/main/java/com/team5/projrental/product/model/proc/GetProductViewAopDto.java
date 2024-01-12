package com.team5.projrental.product.model.proc;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductViewAopDto {
    private Integer iproduct;
    private Integer beforeView;

    public GetProductViewAopDto(Integer iproduct) {
        this.iproduct = iproduct;
    }
}
