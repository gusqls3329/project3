package com.team5.projrental.chat.model;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Builder
@Getter
public class ChatUserInsDto {
    @Range(min = 1)
    private int ichat; // 방 PK

    @Range(min = 1)
    private int iuser; // 유저 PK
}
