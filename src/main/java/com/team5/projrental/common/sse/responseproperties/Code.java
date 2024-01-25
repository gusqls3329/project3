package com.team5.projrental.common.sse.responseproperties;

public enum Code {
    SEND_EXPIRED_PAYMENT_COUNT(1001),
    DIFF_USER_WRITE_REVIEW(2001),
    PAYMENT_IS_FINISHED(2002);

    private int code;
    Code(int code) {
        this.code = code;
    }

    public int get() {
        return code;
    }
}
