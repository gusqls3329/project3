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
    private Integer loginedIuser;
    private Integer targetIuser;
    private Integer prodPerPage;
    private Integer page;

    public GetProductListDto(Integer sort, String search, Integer icategory, Integer page, Integer iuser,
                             Integer prodPerPage) {
        this.sort = sort;
        this.search = search;
        this.icategory = icategory;
        this.page = page;
        this.prodPerPage = prodPerPage;
        this.loginedIuser = iuser;
    }


    public GetProductListDto(Integer iuser, Integer page) {
        this.targetIuser = iuser;
        this.page = page;
        this.prodPerPage = Const.PROD_PER_PAGE;
    }

}
