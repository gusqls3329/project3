package com.team5.projrental.chat.model;

import lombok.Data;


@Data
public class ChatSelDto {

    private int loginedIuser;
    private int page;

    private int startIdx;
    private int rowCount = 30;

    public void setPage(int page) {
        this.startIdx = (page-1) * this.rowCount;
    }
}
