package com.team5.projrental.product.model;

import com.team5.projrental.common.Const;
import com.team5.projrental.product.model.proc.GetProductListResultDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class ProductUserVo extends ProductListVo {
    private String category;

    public ProductUserVo(GetProductListResultDto dto) {
        super(dto);
        category = Const.CATEGORIES.get(dto.getIcategory());
    }

}
