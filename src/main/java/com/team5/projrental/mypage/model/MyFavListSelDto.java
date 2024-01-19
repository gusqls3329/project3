package com.team5.projrental.mypage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class MyFavListSelDto {
    @Range(min = 1)
    private int page;
    private int loginedIuser;
    @JsonIgnore
    private int startIdx;

    private int rowCount = 10;

    public void setPage(int page) {
        this.startIdx = (page-1) * this.rowCount;
    }
}
