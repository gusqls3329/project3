package com.team5.projrental.user.verification.comp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CompCodeVo {
    private String statusCode;
    private String validMsg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String compCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String compNm;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String compCEO;
}
