package com.team5.projrental.product.model.proc;

import com.team5.projrental.common.Const;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductListDto {

    private Integer sort;
    private String search;
    private Integer icategory;
    private Integer iuser;
    private int prodPerPage = Const.PROD_PER_PAGE;
    private int page;

    public GetProductListDto(Integer sort, String search, Integer icategory, int page) {
        this.sort = sort;
        this.search = search;
        this.icategory = icategory;
        this.page = page;
    }


    public GetProductListDto(Integer iuser) {
        this.iuser = iuser;
    }
}
