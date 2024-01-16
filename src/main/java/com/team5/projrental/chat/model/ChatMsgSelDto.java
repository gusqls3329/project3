package com.team5.projrental.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChatMsgSelDto {

    @Length(min = 1)
    private int page;

    @Length(min = 1)
    private int ichat;

    @JsonIgnore
    @Length(min = 1)
    private int startIdx;

    @Length(min = 1)
    private int rowCount = 20;

    public void setPage(int page) {
        this.startIdx = (page-1) * this.rowCount;
    }

}
