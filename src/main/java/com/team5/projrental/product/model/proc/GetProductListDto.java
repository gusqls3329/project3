package com.team5.projrental.product.model.proc;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class GetProductListDto {

    private Integer sort;
    private String search;
    private Integer icategory;

}
