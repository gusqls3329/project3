package com.team5.projrental.product.model.proc;

import com.team5.projrental.product.model.Categories;
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
    private String restAddr;
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
    private Categories icategory;
    private int mainCategory;
    private int subCategory;

    public void setMainCategory(Integer mainCategory) {
        if (this.icategory == null) {
            this.icategory = new Categories();
        }
        icategory.setMainCategory(mainCategory);
    }
    public void setSubCategory(Integer subCategory) {
        if (this.icategory == null) {
            this.icategory = new Categories();
        }
        icategory.setSubCategory(subCategory);
    }
}
