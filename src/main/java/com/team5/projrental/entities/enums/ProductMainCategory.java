package com.team5.projrental.entities.enums;

import com.team5.projrental.common.exception.ErrorCode;
import com.team5.projrental.common.exception.thrid.ClientException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProductMainCategory {

    SMART(1), PC_AND_LAPTOP(2), CAMERA(3),
    SOUND(4), GAME(5), ECT(6);
    private int categoryNum;

    ProductMainCategory(int categoryNum) {
        this.categoryNum = categoryNum;
    }

    public static ProductMainCategory getByNum(int categoryNum) {
        return Arrays.stream(ProductMainCategory.values())
                .filter(e -> e.getCategoryNum() == categoryNum)
                .findAny()
                .orElseThrow(() -> new ClientException(ErrorCode.ILLEGAL_CATEGORY_EX_MESSAGE, "잘못된 카테고리 입력"));
    }

}
