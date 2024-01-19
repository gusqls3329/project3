package com.team5.projrental.mypage.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MyBuyReviewListSelVo {
    private int ireview;
    private int raiting;
    private String contents; // 로그인 유저가 남긴 후기
    private String nick; // 로그인 유저 닉네임
    private String loginedUserPic; // 로그인 유저프로필사진
    private String title; // 상품 제목
    @Schema(title = "상품 대표사진")
    private String prodPic; // 상품 대표사진
    private int iproduct; // 제품 PK
}
