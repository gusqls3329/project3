package com.team5.projrental.mypage.model;

import com.team5.projrental.product.model.Categories;
import lombok.Data;

@Data
public class MyFavListSelVo {
    private int iproduct; // 상품 PK
    private String title; // 상품 제목
    private String prodPic; // 상품 대표사진
    private int price; // 대여가격
    private int deposit; // 보증금
    private String nick; // 물건등록한 상대유저 닉네임
    private String pic; // 물건등록한 상대유저 프로필사진
//    private int imainCategory;
//    private int isubCategory;

    private Categories icategory;

    public void setImainCategory(int imainCategory) {
        if (this.icategory == null) {
            this.icategory = new Categories();
        }
        this.icategory.setMainCategory(imainCategory);
    }

    public void setIsubCategory(int isubCategory) {
        if (this.icategory == null) {
            this.icategory = new Categories();
        }
        this.icategory.setSubCategory(isubCategory);
    }
}
