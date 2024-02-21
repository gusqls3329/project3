package com.team5.projrental.mypage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class MyFavListSelDto {

    @Range(min = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private int page;
    @Range(min = 1)
    private Long loginedIuser;
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount = 6;//

    public void setPage(int page) {
        this.startIdx = (page-1) * this.rowCount;
    }
}
