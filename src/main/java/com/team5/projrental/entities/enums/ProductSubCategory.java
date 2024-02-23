package com.team5.projrental.entities.enums;

import com.team5.projrental.common.exception.ErrorCode;
import com.team5.projrental.common.exception.thrid.ClientException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public enum ProductSubCategory {

    SMART_WATCH(ProductMainCategory.SMART, 1),
    TABLET(ProductMainCategory.SMART, 2),
    GALAXY(ProductMainCategory.SMART, 3),
    IPHONE(ProductMainCategory.SMART, 4),
    //
    LAPTOP(ProductMainCategory.PC_AND_LAPTOP, 1),
    PC(ProductMainCategory.PC_AND_LAPTOP, 2),
    MOUSE(ProductMainCategory.PC_AND_LAPTOP, 3),
    KEYBOARD(ProductMainCategory.PC_AND_LAPTOP, 4),
    //
    BEAM_PROJECTOR(ProductMainCategory.CAMERA, 1),
    SET_TOP_BOX(ProductMainCategory.CAMERA, 2),
    CAMERA(ProductMainCategory.CAMERA, 3),
    CAMCORDER(ProductMainCategory.CAMERA, 4),
    DSLR(ProductMainCategory.CAMERA, 5),
    //
    SPEAKER(ProductMainCategory.SOUND, 1),
    EARPHONE(ProductMainCategory.SOUND, 2),
    HEADPHONE(ProductMainCategory.SOUND, 3),
    MIC(ProductMainCategory.SOUND, 4),
    //
    PLAYSTATION(ProductMainCategory.GAME, 1),
    NINTENDO(ProductMainCategory.GAME, 2),
    WII(ProductMainCategory.GAME, 3),
    XBOX(ProductMainCategory.GAME, 4),
    ECT(ProductMainCategory.GAME, 5);

    private ProductMainCategory mainCategory;
    private int categoryNum;

    ProductSubCategory(ProductMainCategory ProductMainCategory, int categoryNum) {
        this.mainCategory = ProductMainCategory;
        this.categoryNum = categoryNum;
    }

    public static ProductSubCategory getByNum(ProductMainCategory mainCategory, int categoryNum) {
        return Arrays.stream(ProductSubCategory.values())
                .filter(sub -> sub.getMainCategory() == mainCategory && sub.getCategoryNum() == categoryNum)
                .findAny()
                .orElseThrow(() -> new ClientException(ErrorCode.ILLEGAL_CATEGORY_EX_MESSAGE, "잘못된 카테고리 입력"));
    }

    public static List<ProductSubCategory> getByNums(List<ProductMainCategory> mainCategories, List<Integer> categoryNums) {
        List<ProductSubCategory> result = new ArrayList<>();
        for (int i = 0; i < categoryNums.size(); i++) {
            result.add(getByNum(mainCategories.get(i), categoryNums.get(i)));
        }
        return result;
    }
}
