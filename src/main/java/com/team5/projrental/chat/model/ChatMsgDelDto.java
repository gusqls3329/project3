package com.team5.projrental.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
public class ChatMsgDelDto {

    @Range(min = 1)
    private int ichat;

    @JsonIgnore
    private int iuser;
}
