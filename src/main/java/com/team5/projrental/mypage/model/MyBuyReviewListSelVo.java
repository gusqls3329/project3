package com.team5.projrental.mypage.model;

import com.team5.projrental.product.model.Categories;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MyBuyReviewListSelVo {
    private int ireview; // 리뷰 번호(PK)
    private int raiting; // 별 점수
    private String contents; // 로그인 유저가 남긴 후기
    private String nick; // 로그인 유저 닉네임
    private String loginedUserPic; // 로그인 유저프로필사진
    private String title; // 상품 제목
    @Schema(title = "상품 대표사진")
    private String prodPic; // 상품 대표사진
    private int iproduct; // 제품 PK
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
