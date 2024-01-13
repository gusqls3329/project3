package com.team5.projrental.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ChatInsDto {
    @JsonIgnore
    private int ichat;

    private int loginedIuser;
    private int otherPersonIuser;
}
