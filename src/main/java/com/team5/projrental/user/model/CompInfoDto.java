package com.team5.projrental.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompInfoDto {
    private Long compCode;
    private String compNm;
    private String compCeo;
    private String staredAt;
}
