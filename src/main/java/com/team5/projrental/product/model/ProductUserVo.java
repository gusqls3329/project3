package com.team5.projrental.product.model;

import com.team5.projrental.common.Const;
import com.team5.projrental.product.model.proc.GetProductListResultDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductUserVo extends ProductListVo {
    private String category;

    public ProductUserVo(GetProductListResultDto dto) {
        super(dto);
        this.category = dto.getCategory();
    }

}
