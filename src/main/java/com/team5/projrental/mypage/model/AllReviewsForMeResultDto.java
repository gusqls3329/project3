package com.team5.projrental.mypage.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.team5.projrental.product.model.review.ReviewResultVo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllReviewsForMeResultDto extends ReviewResultVo {
    private Integer iproduct;

}
