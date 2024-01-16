package com.team5.projrental.chat.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChatMsgDelDto {

    @Length(min = 1)
    private int ichat;

    @Length(min = 1)
    private int seq;

    @Length(min = 1)
    private int iuser;
}
