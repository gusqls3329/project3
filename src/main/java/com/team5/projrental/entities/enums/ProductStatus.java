package com.team5.projrental.entities.enums;

import com.team5.projrental.common.exception.ErrorCode;
import com.team5.projrental.common.exception.thrid.ClientException;
import com.team5.projrental.common.exception.thrid.ServerException;

import java.util.Arrays;

public enum ProductStatus {
    ACTIVE(1), HIDE (-2), DELETE (-1);

    private int code;
    ProductStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ProductStatus getByNum(int num) {
        return Arrays.stream(ProductStatus.values())
                .filter(o -> o.code == num)
                .findFirst()
                .orElseThrow(() -> new ServerException(ErrorCode.SERVER_ERR_MESSAGE, "제품의 상태가 정상적이지 않습니다."));
    }
}
