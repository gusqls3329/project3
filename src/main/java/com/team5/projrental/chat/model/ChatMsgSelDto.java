package com.team5.projrental.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Data
public class ChatMsgSelDto {

    @Range(min = 1)
    private int page;

    @Range(min = 1)
    private int ichat;

    @JsonIgnore
    private int startIdx;

    @JsonIgnore
    private int rowCount = 20;

    public void setPage(int page) {
        this.startIdx = (page-1) * this.rowCount;
    }

}
