package com.team5.projrental.payment;

import com.team5.projrental.payment.model.PaymentInsDto;
import com.team5.projrental.payment.model.proc.DelPaymentDto;
import com.team5.projrental.payment.model.proc.GetInfoForCheckIproductAndIuserResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentMapper {
    int insPayment(PaymentInsDto paymentInsDto);



    int insProductPayment(@Param("iproduct") Integer iproduct, @Param("ipayment") Integer ipayment);

    GetInfoForCheckIproductAndIuserResult checkIuserAndIproduct(Integer ipayment);

    int delPayment(DelPaymentDto delPaymentDto);

}
