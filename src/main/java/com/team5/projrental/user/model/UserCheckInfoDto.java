package com.team5.projrental.user.model;

import com.team5.projrental.common.exception.ErrorCode;
import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import static com.team5.projrental.common.exception.ErrorCode.*;

@Data
public class UserCheckInfoDto {

    @NotNull
    @Range(min = 1, max = 2)
    private Integer div;
    private String uid;
    private String nick;
}
