package com.team5.projrental.payment;

import com.team5.projrental.payment.model.PaymentInsDto;
import com.team5.projrental.payment.model.proc.*;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface PaymentMapper {
    // for test
    int getPaymentFromProductPayment(int iproduct, int ipayment);
    int getProductFromProductPayment(int iproduct, int ipayment);

    //
    //
    List<GetDepositAndPriceFromProduct> getValidationInfoFromProduct(int iproduct);
    //

    int insPayment(PaymentInsDto paymentInsDto);

    int insProductPayment(Integer iproduct, Integer ipayment);

    GetInfoForCheckIproductAndIuserResult checkIuserAndIproduct(Integer ipayment);

    int delPayment(DelPaymentDto delPaymentDto);

    List<GetPaymentListResultDto> getPaymentList(GetPaymentListDto getPaymentListDto);

    // scheduler
   // int updateIstatusOverRentalEndDate(LocalDate now);
}
