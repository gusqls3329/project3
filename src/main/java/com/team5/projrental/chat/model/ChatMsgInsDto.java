package com.team5.projrental.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChatMsgInsDto {
    @Length(min = 1)
    private int ichat; // 채팅방 고유 번호(채팅방 PK)

    @JsonIgnore
    @Length(min = 1)
    private int seq; // 각 채팅방의 채팅고유 번호(채팅 PK)

    @Length(min = 1)
    private int loginedIuser; // 로그인 유저 PK

    //@NotNull // null은 안됨
    //@NotBlank // 스페이스바도 안됨
    //@NotEmpty // null아니면서 빈문자("") 까지 안됨

    @NotBlank
    private String loginedPic; // 로그인 유저 프로필 사진

    @NotBlank
    private String msg; // 전송할 메세지 내용
}
