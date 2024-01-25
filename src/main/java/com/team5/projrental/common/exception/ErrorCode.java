package com.team5.projrental.common.exception;

import lombok.Getter;


@Getter
public enum ErrorCode {

    ILLEGAL_EX_MESSAGE(ErrorStatus.ILLEGAL_EX_MESSAGE.getCode(), ErrorMessage.ILLEGAL_EX_MESSAGE),
    ILLEGAL_PRODUCT_PICS_EX_MESSAGE(ErrorStatus.ILLEGAL_PRODUCT_PICS_EX_MESSAGE.getCode(), ErrorMessage.ILLEGAL_PRODUCT_PICS_EX_MESSAGE),
    ILLEGAL_CATEGORY_EX_MESSAGE(ErrorStatus.ILLEGAL_CATEGORY_EX_MESSAGE.getCode(), ErrorMessage.ILLEGAL_CATEGORY_EX_MESSAGE),
    ILLEGAL_PAYMENT_EX_MESSAGE(ErrorStatus.ILLEGAL_PAYMENT_EX_MESSAGE.getCode(), ErrorMessage.ILLEGAL_PAYMENT_EX_MESSAGE),
    ILLEGAL_STATUS_EX_MESSAGE(ErrorStatus.ILLEGAL_STATUS_EX_MESSAGE.getCode(), ErrorMessage.ILLEGAL_STATUS_EX_MESSAGE),
    ILLEGAL_RANGE_EX_MESSAGE(ErrorStatus.ILLEGAL_RANGE_EX_MESSAGE.getCode(), ErrorMessage.ILLEGAL_RANGE_EX_MESSAGE),
    ILLEGAL_DATE_EX_MESSAGE(ErrorStatus.ILLEGAL_DATE_EX_MESSAGE.getCode(), ErrorMessage.ILLEGAL_DATE_EX_MESSAGE),
    //
    BUY_DATE_MUST_BE_LATER_THAN_TODAY_EX_MESSAGE(ErrorStatus.BUY_DATE_MUST_BE_LATER_THAN_TODAY_EX_MESSAGE.getCode(), ErrorMessage.BUY_DATE_MUST_BE_LATER_THAN_TODAY_EX_MESSAGE),
    RENTAL_DATE_MUST_BE_BEFORE_THAN_TODAY_EX_MESSAGE(ErrorStatus.RENTAL_DATE_MUST_BE_BEFORE_THAN_TODAY_EX_MESSAGE.getCode(), ErrorMessage.RENTAL_DATE_MUST_BE_BEFORE_THAN_TODAY_EX_MESSAGE),
    RENTAL_END_DATE_MUST_BE_AFTER_THAN_RENTAL_START_DATE_EX_MESSAGE(ErrorStatus.RENTAL_END_DATE_MUST_BE_AFTER_THAN_RENTAL_START_DATE_EX_MESSAGE.getCode(), ErrorMessage.RENTAL_END_DATE_MUST_BE_AFTER_THAN_RENTAL_START_DATE_EX_MESSAGE),
    CAN_NOT_BLANK_EX_MESSAGE(ErrorStatus.CAN_NOT_BLANK_EX_MESSAGE.getCode(), ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE),
    AUTHENTICATION_FAIL_EX_MESSAGE(ErrorStatus.AUTHENTICATION_FAIL_EX_MESSAGE.getCode(), ErrorMessage.AUTHENTICATION_FAIL_EX_MESSAGE),
    CAN_NOT_DEL_USER_EX_MESSAGE(ErrorStatus.CAN_NOT_DEL_USER_EX_MESSAGE.getCode(), ErrorMessage.CAN_NOT_DEL_USER_EX_MESSAGE),
    //
    NO_SUCH_USER_EX_MESSAGE(ErrorStatus.NO_SUCH_USER_EX_MESSAGE.getCode(), ErrorMessage.NO_SUCH_USER_EX_MESSAGE),
    NO_SUCH_PRODUCT_EX_MESSAGE(ErrorStatus.NO_SUCH_PRODUCT_EX_MESSAGE.getCode(), ErrorMessage.NO_SUCH_PRODUCT_EX_MESSAGE),
    NO_SUCH_PAYMENT_EX_MESSAGE(ErrorStatus.NO_SUCH_PAYMENT_EX_MESSAGE.getCode(), ErrorMessage.NO_SUCH_PAYMENT_EX_MESSAGE),
    NO_SUCH_ID_EX_MESSAGE(ErrorStatus.NO_SUCH_ID_EX_MESSAGE.getCode(), ErrorMessage.NO_SUCH_ID_EX_MESSAGE),
    NO_SUCH_PASSWORD_EX_MESSAGE(ErrorStatus.NO_SUCH_PASSWORD_EX_MESSAGE.getCode(), ErrorMessage.NO_SUCH_PASSWORD_EX_MESSAGE),
    //
    BAD_ADDRESS_INFO_EX_MESSAGE(ErrorStatus.BAD_ADDRESS_INFO_EX_MESSAGE.getCode(), ErrorMessage.BAD_ADDRESS_INFO_EX_MESSAGE),
    BAD_INFO_EX_MESSAGE(ErrorStatus.BAD_INFO_EX_MESSAGE.getCode(), ErrorMessage.BAD_INFO_EX_MESSAGE),
    BAD_SORT_EX_MESSAGE(ErrorStatus.BAD_SORT_EX_MESSAGE.getCode(), ErrorMessage.BAD_SORT_EX_MESSAGE),
    BAD_RENTAL_DEL_EX_MESSAGE(ErrorStatus.BAD_RENTAL_DEL_EX_MESSAGE.getCode(), ErrorMessage.BAD_RENTAL_DEL_EX_MESSAGE),
    BAD_RENTAL_CANCEL_EX_MESSAGE(ErrorStatus.BAD_RENTAL_CANCEL_EX_MESSAGE.getCode(), ErrorMessage.BAD_RENTAL_CANCEL_EX_MESSAGE),
    BAD_DIV_INFO_EX_MESSAGE(ErrorStatus.BAD_DIV_INFO_EX_MESSAGE.getCode(), ErrorMessage.BAD_DIV_INFO_EX_MESSAGE),
    BAD_PIC_EX_MESSAGE(ErrorStatus.BAD_PIC_EX_MESSAGE.getCode(), ErrorMessage.BAD_PIC_EX_MESSAGE),
    BAD_ID_EX_MESSAGE(ErrorStatus.BAD_ID_EX_MESSAGE.getCode(), ErrorMessage.BAD_ID_EX_MESSAGE),
    BAD_PRODUCT_INFO_EX_MESSAGE(ErrorStatus.BAD_PRODUCT_INFO_EX_MESSAGE.getCode(), ErrorMessage.BAD_PRODUCT_INFO_EX_MESSAGE),
    BAD_TYPE_EX_MESSAGE(ErrorStatus.BAD_TYPE_EX_MESSAGE.getCode(), ErrorMessage.BAD_TYPE_EX_MESSAGE),
    BAD_WORD_EX_MESSAGE(ErrorStatus.BAD_WORD_EX_MESSAGE.getCode(), ErrorMessage.BAD_WORD_EX_MESSAGE),
    BAD_PRODUCT_ISTATUS_EX_MESSAGE(ErrorStatus.BAD_PRODUCT_ISTATUS_EX_MESSAGE.getCode(), ErrorMessage.BAD_PRODUCT_ISTATUS_EX_MESSAGE),
    //
    ALL_INFO_NOT_EXISTS_EX_MESSAGE(ErrorStatus.ALL_INFO_NOT_EXISTS_EX_MESSAGE.getCode(), ErrorMessage.ALL_INFO_NOT_EXISTS_EX_MESSAGE),
    //
    SERVER_ERR_MESSAGE(ErrorStatus.SERVER_ERR_MESSAGE.getCode(), ErrorMessage.SERVER_ERR_MESSAGE),

    REVIEW_ALREADY_EXISTS_EX_MESSAGE(ErrorStatus.REVIEW_ALREADY_EXISTS_EX_MESSAGE.getCode(), ErrorMessage.REVIEW_ALREADY_EXISTS_EX_MESSAGE),
    NO_SUCH_REVIEW_EX_MESSAGE(ErrorStatus.NO_SUCH_REVIEW_EX_MESSAGE.getCode(), ErrorMessage.NO_SUCH_REVIEW_EX_MESSAGE);

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
