package com.team5.projrental.user.verification.users.model.ready;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VerificationRequestDto {
    // 암호화 필요 데이터 시작
    private String userName;
    private String userPhone;
    private String userBirthday;
    // 암호화 필요 데이터 끝
    private String sessionKey; // API에서 사용자의 개인정보 전달이 필요한 경우 즉, requestType이 USER_PERSONAL인 경우는 필수 파라미터
    //인증 요청과 응답을 AES 암복호화 할 때 사용되는 세션 키로 매 요청 시 마다 새로 생성해서 암호화된 개인정보와 함께 전달해야 합니다.

    private String requestType;
    private String triggerType;
    private String successCallbackUrl; // 토스 앱 인증이 성공적으로 완료되고 돌아갈 고객사 앱스킴 주소
    private String failCallbackUrl; // 토스 앱 인증이 실패 했을 때 돌아갈 고객사 앱스킴 주소
}
