package com.team5.projrental.product.model.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewGetDto {
    private Long iproduct;
    private Integer page;
    private Integer prodPerPage;
}
