package com.team5.projrental.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
public class ChatInsDto {

    @JsonIgnore
    @Range(min = 1)
    private int ichat;

    @JsonIgnore
    @Range(min = 1)
    private int loginedIuser;

    @Range(min = 1)
    private int iproduct;

    @Range(min = 1)
    private int otherPersonIuser;
}
