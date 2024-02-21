package com.team5.projrental.entities.enums;

public enum ProductStatus {
    ACTIVATED(1), HIDDEN(-2), DEL(-1);

    private int code;
    ProductStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
