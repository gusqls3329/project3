package com.team5.projrental.payment;

import com.team5.projrental.common.model.ResVo;
import com.team5.projrental.payment.model.PaymentInsDto;
import com.team5.projrental.payment.model.PaymentListVo;
import com.team5.projrental.payment.model.PaymentVo;

import java.util.List;

public interface RefPaymentService {

    ResVo postPayment(PaymentInsDto paymentInsDto);

    ResVo delPayment(Integer ipayment, Integer div);

    List<PaymentListVo> getAllPayment(Integer role, int page);

    PaymentVo getPayment(Integer ipayment);

}
