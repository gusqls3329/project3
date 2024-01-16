package com.team5.projrental.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChatInsDto {

    private int ichat;

    private int iproduct;

    @JsonIgnore
    private int loginedIuser;

    private int otherPersonIuser;
}
