package com.team5.projrental.common;

import java.util.List;

public interface Const {

    String NOT_ENOUGH_PRODUCT_PICS_EX_MESSAGE = "메인사진을 제외한 사진은 9개 이하여야 합니다.";
    String ILLEGAL_CATEGORY_EX_MESSAGE = "잘못된 카테고리 입니다.";
    String BUY_DATE_MUST_BE_LATER_THAN_TODAY_EX_MESSAGE = "구매일은 오늘보다 같거나 이전이어야 합니다.";
    String RENTAL_DATE_MUST_BE_BEFORE_THAN_TODAY_EX_MESSAGE = "대여 시작일은 오늘보다 같거나 이후어야 합니다.";
    String RENTAL_END_DATE_MUST_BE_AFTER_THAN_RENTAL_START_DATE_EX_MESSAGE = "대여 종료일은 대여 시작일 보다 같거나 이후어야 합니다.";

    //
    List<String> CATEGORIES = List.of("mobile", "home_appliance",
            "game", "laptop", "camera", "ect");
}
