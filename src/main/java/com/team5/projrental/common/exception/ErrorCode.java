package com.team5.projrental.common.exception;

import com.team5.projrental.common.exception.ErrorStatus;
import lombok.Getter;

@Getter
public enum ErrorCode {
    ILLEGAL_EX_MESSAGE(ErrorStatus.ILLEGAL_EX_MESSAGE.getCode(), "잘못된 요청입니다."),
    ILLEGAL_PRODUCT_PICS_EX_MESSAGE(ErrorStatus.ILLEGAL_PRODUCT_PICS_EX_MESSAGE.getCode(), "메인사진을 제외한 사진은 9개 이하여야 합니다."),
    ILLEGAL_CATEGORY_EX_MESSAGE(ErrorStatus.ILLEGAL_CATEGORY_EX_MESSAGE.getCode(), "잘못된 카테고리 입니다."),
    ILLEGAL_PAYMENT_EX_MESSAGE(ErrorStatus.ILLEGAL_PAYMENT_EX_MESSAGE.getCode(), "잘못된 결제수단 입니다."),
    //
    BUY_DATE_MUST_BE_LATER_THAN_TODAY_EX_MESSAGE(ErrorStatus.BUY_DATE_MUST_BE_LATER_THAN_TODAY_EX_MESSAGE.getCode(), "구매일은 오늘보다 같거나 이전이어야 합니다."),
    RENTAL_DATE_MUST_BE_BEFORE_THAN_TODAY_EX_MESSAGE(ErrorStatus.RENTAL_DATE_MUST_BE_BEFORE_THAN_TODAY_EX_MESSAGE.getCode(), "대여 시작일은 오늘보다 같거나 이후어야 합니다."),
    RENTAL_END_DATE_MUST_BE_AFTER_THAN_RENTAL_START_DATE_EX_MESSAGE(ErrorStatus.RENTAL_END_DATE_MUST_BE_AFTER_THAN_RENTAL_START_DATE_EX_MESSAGE.getCode(), "대여 종료일은 대여 시작일 보다 같거나 이후어야 " +
            "합니다" +
            "."),
    //
    NO_SUCH_USER_EX_MESSAGE(ErrorStatus.NO_SUCH_USER_EX_MESSAGE.getCode(), "잘못된 유저 정보 입니다."),
    NO_SUCH_PRODUCT_EX_MESSAGE(ErrorStatus.NO_SUCH_PRODUCT_EX_MESSAGE.getCode(), "조회된 상품이 없습니다."),
    NO_SUCH_PAYMENT_EX_MESSAGE(ErrorStatus.NO_SUCH_PAYMENT_EX_MESSAGE.getCode(), "조회된 결제 정보가 없습니다."),
    NO_SUCH_ID_EX_MESSAGE(ErrorStatus.NO_SUCH_ID_EX_MESSAGE.getCode(), "아이디가 존재하지 않습니다"),
    NO_SUCH_PASSWORD_EX_MESSAGE(ErrorStatus.NO_SUCH_PASSWORD_EX_MESSAGE.getCode(), "비밀번호가 틀렸습니다"),
    //
    BAD_ADDRESS_INFO_EX_MESSAGE(ErrorStatus.BAD_ADDRESS_INFO_EX_MESSAGE.getCode(), "잘못된 주소 입니다."),
    BAD_INFO_EX_MESSAGE(ErrorStatus.BAD_INFO_EX_MESSAGE.getCode(), "잘못된 정보 입니다."),
    BAD_SORT_EX_MESSAGE(ErrorStatus.BAD_SORT_EX_MESSAGE.getCode(), "SORT 는 1 또는 2 만 가능합니다."),
    BAD_RENTAL_DEL_EX_MESSAGE(ErrorStatus.BAD_RENTAL_DEL_EX_MESSAGE.getCode(), "거래중인 결제정보는 삭제하거나 숨길 수 없습니다."),
    BAD_RENTAL_CANCEL_EX_MESSAGE(ErrorStatus.BAD_RENTAL_CANCEL_EX_MESSAGE.getCode(), "거래가 종료된 결제는 취소할 수 없습니다."),
    BAD_DIV_INFO_EX_MESSAGE(ErrorStatus.BAD_DIV_INFO_EX_MESSAGE.getCode(), "div 값이 잘못되었습니다."),
    BAD_PIC_EX_MESSAGE(ErrorStatus.BAD_PIC_EX_MESSAGE.getCode(), "사진이 존재하지 않습니다."),
    BAD_ID_EX_MESSAGE(ErrorStatus.BAD_ID_EX_MESSAGE.getCode(), "이미 존재하는 아이디 입니다."),
    BAD_PRODUCT_INFO_EX_MESSAGE(ErrorStatus.BAD_PRODUCT_INFO_EX_MESSAGE.getCode(), "잘못된 제품 정보 입니다."),
    //
    ALL_INFO_NOT_EXISTS_EX_MESSAGE(ErrorStatus.ALL_INFO_NOT_EXISTS_EX_MESSAGE.getCode(), "모든 정보가 제공되지 않음"),
    //
    SERVER_ERR_MESSAGE(ErrorStatus.SERVER_ERR_MESSAGE.getCode(), "알 수 없는 오류로 실패 했습니다.");


    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
