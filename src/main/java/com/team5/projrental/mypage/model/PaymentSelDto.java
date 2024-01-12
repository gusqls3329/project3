package com.team5.projrental.mypage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PaymentSelDto {

    private int page;//페이지
    @JsonIgnore
    private int startIdx;// 시작 인덱스 넘버
    private int rowCount;//페이지 당 일지 수

    private int loginedIuser; // 로그인 유저정보가 ibuyer에 조건으로 들어가면 빌린내역 seller에 들어가면 빌려준내역

    private int role; // 디폴트 1:일반유저 // 3차프로젝트때 2:기업  이렇게?
}
