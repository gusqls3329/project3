package com.team5.projrental.common.sse.responseproperties;

public enum Properties {
    SEND_EXPIRED_PAYMENT_COUNT(Code.SEND_EXPIRED_PAYMENT_COUNT, Message.SEND_EXPIRED_PAYMENT_COUNT),
    DIFF_USER_WRITE_REVIEW(Code.DIFF_USER_WRITE_REVIEW, Message.DIFF_USER_WRITE_REVIEW),
    PAYMENT_IS_FINISHED(Code.PAYMENT_IS_FINISHED, Message.PAYMENT_IS_FINISHED);


    private Code code;
    private Message message;

    Properties(Code code, Message message) {
        this.code = code;
        this.message = message;
    }

    public Code getCode() {
        return code;
    }

    public Message getMessage() {
        return message;
    }
}
