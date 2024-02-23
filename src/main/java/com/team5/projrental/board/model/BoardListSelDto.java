package com.team5.projrental.board.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.annotation.Nullable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardListSelDto {

    private Integer targetIuser;

    @Range(min = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private int page;

    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount = 12;

    public void setPage(int page) {
        this.startIdx = (page-1) * this.rowCount;
    }

}
