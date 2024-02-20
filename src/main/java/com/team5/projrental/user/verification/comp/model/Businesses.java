package com.team5.projrental.user.verification.comp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Businesses {
    // - 필히 제거.
    // 사업자 등록 번호
    private String b_no;
    // 개업일자, YYYYMMDD 포맷
    private String start_dt;
    // 대표자 명
    private String p_nm;
    @Builder.Default
    private String p_nm2 = "";
    @Builder.Default
    private String b_nm = "";
    @Builder.Default
    private String corp_no = "";
    @Builder.Default
    private String b_sector = "";
    @Builder.Default
    private String b_type = "";
    @Builder.Default
    private String b_adr = "";
}
