package com.team5.projrental.common;

import java.util.Map;

public interface Const {
    /*
        --------- ERROR MESSAGE ---------
     */
    String ILLEGAL_PRODUCT_PICS_EX_MESSAGE = "메인사진을 제외한 사진은 9개 이하여야 합니다.";
    String ILLEGAL_CATEGORY_EX_MESSAGE = "잘못된 카테고리 입니다.";
    String ILLEGAL_PAYMENT_EX_MESSAGE = "잘못된 결제수단 입니다.";
    String BUY_DATE_MUST_BE_LATER_THAN_TODAY_EX_MESSAGE = "구매일은 오늘보다 같거나 이전이어야 합니다.";
    String RENTAL_DATE_MUST_BE_BEFORE_THAN_TODAY_EX_MESSAGE = "대여 시작일은 오늘보다 같거나 이후어야 합니다.";
    String RENTAL_END_DATE_MUST_BE_AFTER_THAN_RENTAL_START_DATE_EX_MESSAGE = "대여 종료일은 대여 시작일 보다 같거나 이후어야 합니다.";
    String SERVER_ERR_MESSAGE = "알 수 없는 오류로 실패 했습니다.";
    String BAD_ADDRESS_INFO_EX_MESSAGE = "잘못된 주소 입니다.";
    String NO_SUCH_USER_EX_MESSAGE = "잘못된 유저 정보 입니다.";
    String NO_SUCH_PRODUCT_EX_MESSAGE = "조회된 상품이 없습니다.";
    String NO_SUCH_PAYMENT_EX_MESSAGE = "조회된 결제 정보가 없습니다.";
    String BAD_INFO_EX_MESSAGE = "잘못된 정보 입니다.";
    String BAD_SORT_EX_MESSAGE = "SORT 는 1 또는 2 만 가능합니다.";
    String BAD_RENTAL_DEL_EX_MESSAGE = "거래중인 결제정보는 삭제하거나 숨길 수 없습니다.";
    String BAD_RENTAL_CANCEL_EX_MESSAGE = "거래가 종료된 결제는 취소할 수 없습니다.";
    String BAD_DIV_INFO_EX_MESSAGE = "div 값이 잘못되었습니다.";
    String BAD_PIC_EX_MESSAGE = "사진이 존재하지 않습니다.";
    int PROD_PER_PAGE = 16;
    int PAY_PER_PAGE = 16;
    //
    String LOGIN_NO_UID = "아이디가 존재하지 않습니다";
    String LOGIN_DIFF_UPW = "비밀번호가 틀렸습니다";
    int SUCCESS = 1;
    int FAIL = 0;
    //

    /*
        --------- STATUS MAP ---------
     */
    Map<Integer, String> STATUS = Map.of(
            -3, "cancel",
            -2, "",
            -1, "del",
            0, "rent",
            1, "succ",
            2, "c-seller",
            3, "c-buyer");

    //
    /*
        --------- CONST VALUES ---------
     */
    String AXIS_X = "x";
    String AXIS_Y = "y";


    /*
        --------- CATEGORIES ---------
    */
    Map<Integer, String> CATEGORIES = Map.of(
            1, "mobile",
            2, "home-appliance",
            3, "game",
            4, "laptop",
            5, "camera",
            6, "ect");

    Map<Integer, String> PAYMENT_METHODS = Map.of(
            1, "credit-card",
            2, "kakao-pay");

    /*
        --------- CATEGORIES ---------
    */

    String CATEGORY_USER = "user";
    String CATEGORY_PRODUCT_SUB = "prod";
    String CATEGORY_PRODUCT_MAIN = "prod/main";



}
