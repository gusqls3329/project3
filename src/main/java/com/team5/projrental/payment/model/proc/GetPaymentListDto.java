package com.team5.projrental.payment.model.proc;

import com.team5.projrental.common.Const;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPaymentListDto {
    private Long iuser;
    private Integer role;
    private int flag;
    private int ipayment;
    private int page;
    private int paymentPerPage;

    public GetPaymentListDto(Long iuser, Integer role, int page, boolean hasRole) {
        this.iuser = iuser;
        this.role = role;
        this.page = page;
        this.paymentPerPage = Const.PAY_PER_PAGE;
    }

    public GetPaymentListDto(Long iuser, int flag, int ipayment) {
        this.iuser = iuser;
        this.flag = flag;
        this.ipayment = ipayment;
    }



}
