package com.team5.projrental.mypage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class PaymentSelDto {

    @Range(min = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private int page;//페이지

    @JsonIgnore
    private Long loginedIuser; // 로그인 유저정보가 ibuyer에 조건으로 들어가면 빌린내역 seller에 들어가면 빌려준내역

    @Range(min = 1,max = 2, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private int role; // 디폴트 1:일반유저 // 3차프로젝트때 2:기업  이렇게?

    @JsonIgnore
    private int startIdx;// 시작 인덱스 넘버

    @JsonIgnore
    private int rowCount = 6;//페이지 당 일지 수




    public void setPage(int page){
        this.startIdx= (page-1)*this.rowCount;
    }
}
