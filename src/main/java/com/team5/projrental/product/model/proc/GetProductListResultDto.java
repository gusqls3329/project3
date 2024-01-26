package com.team5.projrental.product.model.proc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductListResultDto {

    private Integer iuser;
    private String nick;
    private String userStoredPic;
    private Integer iauth;

    private Integer iproduct;
    private String addr;
    private String title;
    private String prodMainStoredPic;
    private Integer price;
    private Integer rentalPrice;
    private Integer deposit;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private Integer prodLike;
    private Integer istatus;
    private Integer isLiked;
    private Integer view;
    private Integer inventory;

    //
    private Integer icategory;
}
