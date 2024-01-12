package com.team5.projrental.payment.model.proc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPaymentListDto {
    private Integer iuser;
    private Integer role;
    private int flag;
    private int ipayment;

    public GetPaymentListDto(Integer iuser, Integer role) {
        this.iuser = iuser;
        this.role = role;
    }

    public GetPaymentListDto(Integer iuser, int flag, int ipayment) {
        this.iuser = iuser;
        this.flag = flag;
        this.ipayment = ipayment;
    }
}
