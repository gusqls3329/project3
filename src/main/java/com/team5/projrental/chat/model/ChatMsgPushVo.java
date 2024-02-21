package com.team5.projrental.chat.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMsgPushVo extends ChatMsgSelVo {
    private int ichat; // 채팅 방 번호(PK)
    @JsonIgnore
    private Long iuser;
}
