package com.team5.projrental.product.model.proc;

import com.team5.projrental.product.model.Categories;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class GetProductBaseDto {
    private Categories icategory;
    private Integer iproduct;
    private Long iuser;



    public GetProductBaseDto(Categories icategory, Integer iproduct, Long iuser) {
        this.icategory = icategory;
        this.iproduct = iproduct;
        this.iuser = iuser;
    }
    public GetProductBaseDto( Integer iproduct, Long iuser) {
        this.iproduct = iproduct;
        this.iuser = iuser;
    }

    public GetProductBaseDto(Integer iproduct) {
        this.iproduct = iproduct;
    }
}
