package com.team5.projrental.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChatInsDto {

    @JsonIgnore
    private int ichat;
    @JsonIgnore
    private int loginedIuser;

    private int iproduct;

    private int otherPersonIuser;
}
