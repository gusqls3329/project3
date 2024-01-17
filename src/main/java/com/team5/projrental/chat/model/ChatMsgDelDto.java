package com.team5.projrental.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChatMsgDelDto {
    private int ichat;

    @JsonIgnore
    private int iuser;
}
