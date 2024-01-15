package com.team5.projrental.payment.model.proc;

import com.team5.projrental.common.Const;
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
    private int page;
    private int paymentPerPage;

    public GetPaymentListDto(int iuser, Integer role, int page, boolean hasRole) {
        this.iuser = iuser;
        this.role = role;
        this.page = page;
        this.paymentPerPage = Const.PAY_PER_PAGE;
    }

    public GetPaymentListDto(Integer iuser, int flag, int ipayment) {
        this.iuser = iuser;
        this.flag = flag;
        this.ipayment = ipayment;
    }



}
