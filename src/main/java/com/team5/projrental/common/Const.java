package com.team5.projrental.common;

import java.util.Map;

public interface Const {
    /*
        --------- ERROR MESSAGE ---------
     */
//    String ILLEGAL_PRODUCT_PICS_EX_MESSAGE = "메인사진을 제외한 사진은 9개 이하여야 합니다.";
//    String ILLEGAL_CATEGORY_EX_MESSAGE = "잘못된 카테고리 입니다.";
//    String ILLEGAL_PAYMENT_EX_MESSAGE = "잘못된 결제수단 입니다.";
//    //
//    String BUY_DATE_MUST_BE_LATER_THAN_TODAY_EX_MESSAGE = "구매일은 오늘보다 같거나 이전이어야 합니다.";
//    String RENTAL_DATE_MUST_BE_BEFORE_THAN_TODAY_EX_MESSAGE = "대여 시작일은 오늘보다 같거나 이후어야 합니다.";
//    String RENTAL_END_DATE_MUST_BE_AFTER_THAN_RENTAL_START_DATE_EX_MESSAGE = "대여 종료일은 대여 시작일 보다 같거나 이후어야 합니다.";
//    //
//    String NO_SUCH_USER_EX_MESSAGE = "잘못된 유저 정보 입니다.";
//    String NO_SUCH_PRODUCT_EX_MESSAGE = "조회된 상품이 없습니다.";
//    String NO_SUCH_PAYMENT_EX_MESSAGE = "조회된 결제 정보가 없습니다.";
//    String NO_SUCH_ID_EX_MESSAGE = "아이디가 존재하지 않습니다";
//    String NO_SUCH_PASSWORD_EX_MESSAGE = "비밀번호가 틀렸습니다";
//    //
//    String BAD_ADDRESS_INFO_EX_MESSAGE = "잘못된 주소 입니다.";
//    String BAD_INFO_EX_MESSAGE = "잘못된 정보 입니다.";
//    String BAD_SORT_EX_MESSAGE = "SORT 는 1 또는 2 만 가능합니다.";
//    String BAD_RENTAL_DEL_EX_MESSAGE = "거래중인 결제정보는 삭제하거나 숨길 수 없습니다.";
//    String BAD_RENTAL_CANCEL_EX_MESSAGE = "거래가 종료된 결제는 취소할 수 없습니다.";
//    String BAD_DIV_INFO_EX_MESSAGE = "div 값이 잘못되었습니다.";
//    String BAD_PIC_EX_MESSAGE = "사진이 존재하지 않습니다.";
//    String BAD_ID_EX_MESSAGE = "이미 존재하는 아이디 입니다.";
//    //
//    String ALL_INFO_NOT_EXISTS_EX_MESSAGE = "모든 정보가 제공되지 않음";
//    //
//    String SERVER_ERR_MESSAGE = "알 수 없는 오류로 실패 했습니다.";

    //
    int PROD_PER_PAGE = 16;
    int PAY_PER_PAGE = 16;
    int IN_PRODUCT_REVIEW_PER_PAGE = 4;
    int TOTAL_REVIEW_PER_PAGE = 10;
    int MAIN_PROD_PER_PAGE = 8;

    int SUCCESS = 1;
    int FAIL = 0;
    int CATEGORY_COUNT = 22;
    int DEL_I_STATUS = -1;
    /* TODO: 1/18/24
        해당 부분 프론트와 대화 협의 필요
        --by Hyunmin */

    int ADD_MONTH_NUM_FOR_DISABLED_DATE = 1; // 2개월치 조회시 1 필요, 3개월치 조회시 2 필요 ...
    int DISABLED_CACHE_MAX_NUM = 50;
//    long SSE_TIMEOUT_TIME = 300000;
    long SSE_TIMEOUT_TIME = 30000;
    long SSE_RECONNECT_TIME = SSE_TIMEOUT_TIME - 100;
    int ADD_A = 1;
            //

    /*
        --------- STATUS MAP ---------
     */
//    Map<Integer, String> STATUS = Map.of(
//            -4, "expired",
//            -3, "cancel",
//            -2, "",
//            -1, "del",
//            0, "rent",
//            1, "succ",
//            2, "c-seller",
//            3, "c-buyer");

    //
    /*
        --------- CONST VALUES ---------
     */
    String AXIS_X = "x";
    String AXIS_Y = "y";


    /*
        --------- CATEGORIES ---------
    */

//    Map<Integer, String> CATEGORIES = Map.of(
//            1, "smart-watch",
//            2, "tablet",
//            3, "Galaxy",
//            4, "iphone",
//            5, "laptop",
//            6, "pc",
//            7, "mouse",
//            8, "keyboard",
//            9, "beam-projector",
//            10, "set-top-box",
//            11, "camera",
//            12, "camcorder",
//            13, "dslr",
//            14, "speaker",
//            15, "earphone",
//            16, "headphone",
//            17, "mic",
//            18, "playstation",
//            19, "nintendo",
//            20, "wii",
//            21, "xbox",
//            22, "ect");

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
