package com.team5.projrental.product.model;

import com.team5.projrental.product.model.proc.GetProductListResultDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductUserVo extends ProductListVo {
    private Integer icategory;

    public ProductUserVo(GetProductListResultDto dto) {
        super(dto);
        this.icategory = dto.getIcategory();
    }

}
