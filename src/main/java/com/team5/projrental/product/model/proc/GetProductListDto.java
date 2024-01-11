package com.team5.projrental.product.model.proc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductListDto {

    private Integer sort;
    private String search;
    private Integer icategory;
    private Integer iuser;

    public GetProductListDto(Integer sort, String search, Integer icategory) {
        this.sort = sort;
        this.search = search;
        this.icategory = icategory;
    }

    public GetProductListDto(Integer iuser) {
        this.iuser = iuser;
    }
}
