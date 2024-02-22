package com.team5.projrental.mypage.model;

import com.team5.projrental.entities.enums.DisputeCategory;
import com.team5.projrental.entities.enums.DisputeStatus;
import lombok.Data;

@Data
public class MyDisputeVo {
    private long idispute;
    private long ireported;
    private DisputeCategory category;
    private String details;
    private long penality;
    private DisputeStatus status;
    // 유저테이블
    private String nick;
    private String profPic; //프로필 사진
    // 상품테이블
    private long iproduct;
    private String prodPic; //상품 사진
}
