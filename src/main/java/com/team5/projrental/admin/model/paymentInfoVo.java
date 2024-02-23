package com.team5.projrental.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class paymentInfoVo {
    private String method;
    private long totalPrice;
    private long deposit;
    private String tid;
}
