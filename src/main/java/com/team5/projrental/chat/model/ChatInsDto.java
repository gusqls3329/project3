package com.team5.projrental.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChatInsDto {

    @Length(min = 1)
    private int ichat;

    @Length(min = 1)
    private int iproduct;

    @JsonIgnore
    @Length(min = 1)
    private int loginedIuser;

    @Length(min = 1)
    private int otherPersonIuser;
}
