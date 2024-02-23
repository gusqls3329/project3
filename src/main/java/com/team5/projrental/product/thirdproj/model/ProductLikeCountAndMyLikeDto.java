package com.team5.projrental.product.thirdproj.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductLikeCountAndMyLikeDto {
    private Long likeCnt;
    private Integer isLiked;
}
