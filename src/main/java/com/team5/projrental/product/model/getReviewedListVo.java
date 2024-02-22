package com.team5.projrental.product.model;

import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class getReviewedListVo {

    private Long ireview;
    private String contents;
    private Long rating;
    private Long iuser;
    private String nick;
    private String userProfPic;
    private Categories icategory;
}
