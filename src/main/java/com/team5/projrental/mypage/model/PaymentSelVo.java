package com.team5.projrental.mypage.model;

import lombok.Data;

@Data
public class PaymentSelVo {

    private int ibuyer; // 빌리는사람
    private int iuser; //빌려주는사람

    private int iproduct; // 제품 PK

    private int ipayment; // 지불 PK

    private String pic; // 제품 대표 사진
    private int deposit; // 보증금
    private int price; // 가격
    private int rentalDuration; // 대여 일수
    private String rentalStartDate; // 대여 시작일
    private String rentalEndDate; // 반납일
    private int cancel; // loginedIuser 만 취소 요청을 한 경우: 3, 거래 상대가 취소요청을 한 경우: 2, 서로 취소요청을 하여 취소가 성공적으로 진행된 경우: 1
    private String createdAt; // 대여료 결제일
    private int targetIuser; // 상대 유저 PK
    private String targetNick; // 상대 닉네임
    private String stroedPic; // 상대 프로필사진
}
