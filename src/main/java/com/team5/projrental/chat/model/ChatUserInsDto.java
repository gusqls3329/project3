package com.team5.projrental.chat.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChatUserInsDto {
    private int ichat; // 방 PK
    private int iuser; // 유저 PK
}
