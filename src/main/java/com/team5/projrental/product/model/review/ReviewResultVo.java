package com.team5.projrental.product.model.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResultVo {

    private Integer ireview;
    private String contents;
    private Integer rating;
    private String nick;
    private String profPic;
}
