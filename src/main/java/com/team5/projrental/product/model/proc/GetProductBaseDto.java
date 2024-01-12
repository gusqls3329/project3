package com.team5.projrental.product.model.proc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetProductBaseDto {
    private Integer icategory;
    private Integer iproduct;
    private Integer iuser;

    public GetProductBaseDto(Integer icategory, Integer iproduct) {
        this.icategory = icategory;
        this.iproduct = iproduct;
    }
}
