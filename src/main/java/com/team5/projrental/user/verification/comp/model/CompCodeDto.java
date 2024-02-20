package com.team5.projrental.user.verification.comp.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompCodeDto {
    private String compCode;
    private String compNm;
    private String compCEO;
    private LocalDate startAt;

}
