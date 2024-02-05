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
    private Integer iuser;
    private String nick;
    private String userProfPic;
    private int imainCategory;
    private int isubCategory;
}
