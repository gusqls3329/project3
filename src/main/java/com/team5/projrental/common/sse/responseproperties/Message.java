package com.team5.projrental.common.sse.responseproperties;

public enum Message {
    SEND_EXPIRED_PAYMENT_COUNT("반납일이 지났지만 리뷰가 등록되지 않은 결제 개수"),
    DIFF_USER_WRITE_REVIEW("상대 유저가 리뷰를 등록함"),
    PAYMENT_IS_FINISHED("두 유저가 모두 리뷰를 등록하여 거래가 완료되었습니다.");

    private String message;
    Message(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}
