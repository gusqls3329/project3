package com.team5.projrental.product.model.proc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class GetProductBaseDto {
    private Integer icategory;
    private Integer iproduct;
    private Integer iuser;



    public GetProductBaseDto(Integer icategory, Integer iproduct, Integer iuser) {
        this.icategory = icategory;
        this.iproduct = iproduct;
        this.iuser = iuser;
    }

    public GetProductBaseDto(Integer iproduct) {
        this.iproduct = iproduct;
    }
}
