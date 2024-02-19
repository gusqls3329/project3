package com.team5.projrental.user.verification.comp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompCodeResponseDto {

    private String status_code;
    private Integer request_cnt;
    private Integer valid_cnt;
    private List<Data> data;

}
