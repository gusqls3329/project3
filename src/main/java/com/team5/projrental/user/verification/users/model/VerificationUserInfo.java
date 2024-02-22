package com.team5.projrental.user.verification.users.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerificationUserInfo {
    // 암호화 필요 데이터 시작
    private String userName;
    private String userPhone;
    private String userBirthday;
    private String nationality;
    // 암호화 필요 데이터 끝
}
