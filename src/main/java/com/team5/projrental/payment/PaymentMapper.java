package com.team5.projrental.payment;

import com.team5.projrental.payment.model.PaymentInsDto;
import com.team5.projrental.payment.model.PaymentListVo;
import com.team5.projrental.payment.model.proc.DelPaymentDto;
import com.team5.projrental.payment.model.proc.GetInfoForCheckIproductAndIuserResult;
import com.team5.projrental.payment.model.proc.GetPaymentListDto;
import com.team5.projrental.payment.model.proc.GetPaymentListResultDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {
    int insPayment(PaymentInsDto paymentInsDto);



    int insProductPayment(@Param("iproduct") Integer iproduct, @Param("ipayment") Integer ipayment);

    GetInfoForCheckIproductAndIuserResult checkIuserAndIproduct(Integer ipayment);

    int delPayment(DelPaymentDto delPaymentDto);

    List<GetPaymentListResultDto> getPaymentList(GetPaymentListDto getPaymentListDto);
}
