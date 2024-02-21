package com.team5.projrental.product.model.proc;

import com.team5.projrental.common.Const;
import com.team5.projrental.product.model.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductListDto {

    private Integer sort;
    private String search;
    private Categories icategory;
    private Long loginedIuser;
    private Long targetIuser;
    private Integer prodPerPage;
    private Integer page;

    private Long loginedIuserForHiddenProduct;

    public GetProductListDto(Integer sort, String search, Categories icategory, Integer page, Long iuser,
                             Integer prodPerPage) {
        this.sort = sort;
        this.search = search;
        this.icategory = icategory;
        this.page = page;
        this.prodPerPage = prodPerPage;
        this.loginedIuser = iuser;
    }


    public GetProductListDto(Long iuser, Integer page) {
        this.targetIuser = iuser;
        this.page = page;
        this.prodPerPage = Const.PROD_PER_PAGE;
    }

    public void setPage(Integer page) {
        this.page = page;
        this.prodPerPage = Const.PROD_PER_PAGE;
    }

}
