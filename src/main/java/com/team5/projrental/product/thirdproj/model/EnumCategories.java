package com.team5.projrental.product.thirdproj.model;

import com.team5.projrental.entities.enums.ProductMainCategory;
import com.team5.projrental.entities.enums.ProductSubCategory;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EnumCategories {
    private ProductMainCategory mainCategory;
    private ProductSubCategory subCategory;
}
