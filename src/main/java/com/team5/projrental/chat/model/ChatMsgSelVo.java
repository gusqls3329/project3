package com.team5.projrental.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class ChatMsgSelVo {
    private int seq; // 해당 채팅방 메세지 PK
    private Long writerIuser; // 작성자 PK
    private String writerPic; // 메세지 보낸 사람 사진
    private String msg; // 메세지
    private String createdAt; // 각 seq에 대한 메세지 보낸 시간
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String prodPic; // 제품 대표사진
}
