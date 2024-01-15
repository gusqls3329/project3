package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DelUserDto {
    @JsonIgnore
    private int iuser;
    private String uid;
    private String upw;
    private String phone;
}
