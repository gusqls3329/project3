package com.team5.projrental.board.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class BoardListSelDto {
    private int loginedIuser;
    //private int targetIuser;

    /*private int startIdx;
    private int rowCount = 12;
    public void setPage(int page) {
        this.startIdx = (page-1) * this.rowCount;
    }*/

}
