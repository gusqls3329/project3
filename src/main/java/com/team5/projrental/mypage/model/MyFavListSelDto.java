package com.team5.projrental.mypage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class MyFavListSelDto {
    private int page;
    private int loginedIuser;
    @JsonIgnore
    private int startIdx;

    @JsonIgnore
    private int rowCount = 10;

    public void setPage(int page) {
        this.startIdx = (page-1) * this.rowCount;
    }
}
