package com.team5.projrental.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class SeldelUserPayDto {
    private Integer iproduct;
    private Long iuser;
    @JsonIgnore
    private int pistatus;
    @JsonIgnore
    private int paistatus;
}
