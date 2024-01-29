package com.team5.projrental.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

// 채팅방 리스트
@Data
public class ChatSelVo {
    private int ichat; // 채팅방 PK
    private int iproduct; // 제품 pk
    private String prodPic; // 제품 대표사진
    private String lastMsg; // 최근 메세지 내용
    private String lastMsgAt; // 최근 메세지 발송시간
    private int otherPersonIuser; // 상대방 유저 PK
    private String otherPersonNm; // 상대방 유저 이름
    private String otherPersonPic; // 상대방 유저 사진

    @JsonIgnore
    private int existEnableRoom;

    @JsonIgnore
    private int istatus;
}
