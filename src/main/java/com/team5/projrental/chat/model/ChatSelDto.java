package com.team5.projrental.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


@Data
public class ChatSelDto {
    //@NotNull // null은 안됨
    //@NotBlank // 스페이스바도 안됨
    //@NotEmpty // null아니면서 빈문자("") 까지 안됨

    @Range(min = 1, message = ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE)
    private int page;

    @JsonIgnore
    private int loginedIuser;

    @JsonIgnore
    private int startIdx;

    @JsonIgnore
    private int rowCount = 20;

    public void setPage(int page) {
        this.startIdx = (page-1) * this.rowCount;
    }
}
