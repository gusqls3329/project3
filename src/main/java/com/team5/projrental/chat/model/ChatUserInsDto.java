package com.team5.projrental.chat.model;

import com.team5.projrental.common.exception.ErrorMessage;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Builder
@Getter
public class ChatUserInsDto {
    @Range(min = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private int ichat; // 방 PK

    @Range(min = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private Long iuser; // 유저 PK
}
