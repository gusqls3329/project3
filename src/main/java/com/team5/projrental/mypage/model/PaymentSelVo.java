package com.team5.projrental.mypage.model;

import com.team5.projrental.product.model.Categories;
import lombok.Data;

@Data
public class PaymentSelVo {

    private int ibuyer; // 빌리는사람
    private int iuser; //빌려주는사람
    private int iproduct; // 제품 PK
    private int ipayment; // 지불 PK
    private int istatus;

    private String title;
    private String productStoredPic; // 제품 대표 사진
    private int deposit; // 보증금
    private int price; // 가격
    private int rentalDuration; // 대여 일수
    private String rentalStartDate; // 대여 시작일
    private String rentalEndDate; // 반납일
    private String createdAt; // 대여료 결제일
    private int targetIuser; // 상대 유저 PK
    private String targetNick; // 상대 닉네임
    private String userStoredPic; // 상대 프로필사진

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
