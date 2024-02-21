package com.team5.projrental.entities.enums;
//ACTIVE : 활동중(회원가입 승인됨 ) , 대기중 :WAIT, 회원가입 반려됨 : COMPANION, 벌점누적으로 정지됨 : SANCTIONS, 유저탈퇴(숨김):HIDE
public enum CompStatus {
    WAIT, ACTIVE, COMPANION, SANCTIONS, HIDE
}
