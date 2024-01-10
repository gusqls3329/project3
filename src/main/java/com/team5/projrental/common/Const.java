package com.team5.projrental.common;

import java.util.Map;

public interface Const {
    /*
        --------- ERROR MESSAGE ---------
     */
    String ILLEGAL_PRODUCT_PICS_EX_MESSAGE = "메인사진을 제외한 사진은 9개 이하여야 합니다.";
    String ILLEGAL_CATEGORY_EX_MESSAGE = "잘못된 카테고리 입니다.";
    String BUY_DATE_MUST_BE_LATER_THAN_TODAY_EX_MESSAGE = "구매일은 오늘보다 같거나 이전이어야 합니다.";
    String RENTAL_DATE_MUST_BE_BEFORE_THAN_TODAY_EX_MESSAGE = "대여 시작일은 오늘보다 같거나 이후어야 합니다.";
    String RENTAL_END_DATE_MUST_BE_AFTER_THAN_RENTAL_START_DATE_EX_MESSAGE = "대여 종료일은 대여 시작일 보다 같거나 이후어야 합니다.";
    String SERVER_ERR_MESSAGE = "알 수 없는 오류로 실패 했습니다.";
    String BAD_ADDRESS_INFO_EX_MESSAGE = "잘못된 주소 입니다.";
    String BAD_USER_INFO_EX_MESSAGE = "잘못된 유저 정보 입니다.";
    String PRODUCT_NOT_FOUND_EX_MESSAGE = "조회된 상품이 없습니다.";
    String BAD_INFO_EX_MESSAGE = "잘못된 정보 입니다.";
    //

    /*
        --------- CONST VALUES ---------
     */
    String AXIS_X = "x";
    String AXIS_Y = "y";


    /*
        --------- CATEGORIES ---------
    */
    Map<Integer, String> CATEGORIES = Map.of(1, "mobile", 2, "home_appliance", 3, "game",
            4, "laptop", 5, "camera", 6, "ect");
}
