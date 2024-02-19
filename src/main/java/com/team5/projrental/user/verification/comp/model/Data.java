package com.team5.projrental.user.verification.comp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {
    private String b_no;
    private String valid;
    private String valid_msg;

    private RequestParameter request_param;
    private CompStatus status;

}
