package com.team5.projrental.user.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class UserCheckInfoDto {

    @NotNull
    @Range(min = 1, max = 2)
    private Integer div;
    private String uid;
    private String nick;
}
