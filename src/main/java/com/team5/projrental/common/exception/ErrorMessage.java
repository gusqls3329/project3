package com.team5.projrental.common.exception;


public interface ErrorMessage {
    String ILLEGAL_EX_MESSAGE = "잘못된 요청입니다.";
    String ILLEGAL_PRODUCT_PICS_EX_MESSAGE = "메인사진을 제외한 사진은 9개 이하여야 합니다.";
    String ILLEGAL_CATEGORY_EX_MESSAGE = "잘못된 카테고리 입니다.";
    String ILLEGAL_PAYMENT_EX_MESSAGE = "잘못된 결제수단 입니다.";
    String ILLEGAL_RANGE_EX_MESSAGE = "잘못된 범위의 값을 입력하셨습니다.";
    String ILLEGAL_DATE_EX_MESSAGE = "잘못된 날짜를 입력하셨습니다.";
    //
    String BUY_DATE_MUST_BE_LATER_THAN_TODAY_EX_MESSAGE = "구매일은 오늘보다 같거나 이전이어야 합니다.";
    String RENTAL_DATE_MUST_BE_BEFORE_THAN_TODAY_EX_MESSAGE = "대여 시작일은 오늘보다 같거나 이후어야 합니다.";
    String RENTAL_END_DATE_MUST_BE_AFTER_THAN_RENTAL_START_DATE_EX_MESSAGE = "대여 종료일은 대여 시작일 보다 같거나 이후어야합니다.";
    String CAN_NOT_BLANK_EX_MESSAGE = "필수값이 제공되지 않았습니다.";
    String AUTHENTICATION_FAIL_EX_MESSAGE = "인증에 실패하였습니다.";
    //
    String NO_SUCH_USER_EX_MESSAGE = "잘못된 유저 정보 입니다.";
    String NO_SUCH_PRODUCT_EX_MESSAGE = "해당하는 상품이 없습니다.";
    String NO_SUCH_PAYMENT_EX_MESSAGE = "조회된 결제 정보가 없습니다.";
    String NO_SUCH_ID_EX_MESSAGE = "아이디가 존재하지 않습니다";
    String NO_SUCH_PASSWORD_EX_MESSAGE = "비밀번호가 틀렸습니다";
    //
    String BAD_ADDRESS_INFO_EX_MESSAGE = "잘못된 주소 입니다.";
    String BAD_INFO_EX_MESSAGE = "잘못된 정보 입니다.";
    String BAD_SORT_EX_MESSAGE = "SORT 는 1 또는 2 만 가능합니다.";
    String BAD_RENTAL_DEL_EX_MESSAGE = "거래중인 결제정보는 삭제하거나 숨길 수 없습니다.";
    String BAD_RENTAL_CANCEL_EX_MESSAGE = "거래가 종료된 결제는 취소할 수 없습니다.";
    String BAD_DIV_INFO_EX_MESSAGE = "div 값이 잘못되었습니다.";
    String BAD_PIC_EX_MESSAGE = "사진이 존재하지 않습니다.";
    String BAD_ID_EX_MESSAGE = "이미 존재하는 아이디 입니다.";
    String BAD_NICK_EX_MESSAGE = "이미 존재하는 닉네임 입니다.";
    String BAD_PRODUCT_INFO_EX_MESSAGE = "잘못된 제품 정보 입니다.";
    String REVIEW_ALREADY_EXISTS_EX_MESSAGE = "작성하신 리뷰가 존재합니다.";
    String NO_SUCH_REVIEW_EX_MESSAGE = "작성하신 리뷰가 없습니다.";
    //
    String ALL_INFO_NOT_EXISTS_EX_MESSAGE = "모든 정보가 제공되지 않음";
    //
    String SERVER_ERR_MESSAGE = "알 수 없는 오류로 실패 했습니다.";

    // added
    String ILLEGAL_STATUS_EX_MESSAGE = "상대유저가 채팅방을 나갔습니다.";
    String CAN_NOT_DEL_USER_EX_MESSAGE = "삭제되지 않은 데이터가 있어 탈퇴할 수 없습니다.";


}
