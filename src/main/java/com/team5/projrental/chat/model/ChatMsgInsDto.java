package com.team5.projrental.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
public class ChatMsgInsDto {

    @Range(min = 1)
    private int ichat; // 채팅방 고유 번호(채팅방 PK)

    @JsonIgnore
    @Range(min = 1)
    private int seq; // 각 채팅방의 채팅고유 번호(채팅 PK)

    @JsonIgnore
    @Range(min = 1)
    private int loginedIuser; // 로그인 유저 PK

    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    private String msg; // 전송할 메세지 내용


    //@NotNull // null은 안됨
    //@NotBlank // 스페이스바도 안됨
    //@NotEmpty // null아니면서 빈문자("") 까지 안됨
}
