package com.team5.projrental.payment;


import com.team5.projrental.payment.model.PaymentInsDto;
import com.team5.projrental.payment.model.proc.DelPaymentDto;
import com.team5.projrental.payment.model.proc.GetInfoForCheckIproductAndIuserResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Slf4j
class PaymentMapperTest {

    @Autowired
    PaymentMapper paymentMapper;

    @Test
    void insPayment() {


        int buyer = 3;
        int iproduct = 11;
        PaymentInsDto dto = PaymentInsDto.builder()
                .iproduct(iproduct)
                .ibuyer(buyer)
                .rentalDuration(3)
                .price(10)
                .code("test")
                .ipaymentMethod(2)
                .rentalStartDate(LocalDate.of(2023, 12, 1))
                .rentalEndDate(LocalDate.of(2023, 12, 10))
                .build();
        int result1 = paymentMapper.insPayment(dto);

        assertThat(result1).isEqualTo(1);

        paymentMapper.insProductPayment(dto.getIproduct(), dto.getIpayment());

        // 조회한 상대가 나와야 함


        assertThrows(DataIntegrityViolationException.class, () -> paymentMapper.insPayment(PaymentInsDto.builder().build()));


    }

    @Test
    void insProductPayment() {
        int buyer = 3;
        int iproduct = 11;
        PaymentInsDto dto = PaymentInsDto.builder()
                .iproduct(iproduct)
                .ibuyer(buyer)
                .rentalDuration(3)
                .price(10)
                .code("test")
                .ipaymentMethod(2)
                .rentalStartDate(LocalDate.of(2023, 12, 1))
                .rentalEndDate(LocalDate.of(2023, 12, 10))
                .build();
        paymentMapper.insPayment(dto);
        int ipayment = dto.getIpayment();


        int result = paymentMapper.insProductPayment(iproduct, ipayment);
        assertThat(result).isEqualTo(1);

        int payResult = paymentMapper.getPaymentFromProductPayment(iproduct, ipayment);
        assertThat(payResult).isEqualTo(ipayment);

        int prodResult = paymentMapper.getProductFromProductPayment(iproduct, ipayment);
        assertThat(prodResult).isEqualTo(iproduct);


    }

    @Test
    void checkIuserAndIproduct() {

        GetInfoForCheckIproductAndIuserResult result1 = paymentMapper.checkIuserAndIproduct(11, 1);
        assertThat(result1.getIstatus()).isNotEqualTo(0);
        assertThat(result1.getIBuyer()).isEqualTo(4);
        assertThat(result1.getISeller()).isEqualTo(1);
        assertThat(result1.getRentalEndDate()).isEqualTo(LocalDate.of(2024, 1, 20));


    }

    @Test
    void delPayment() {

        paymentMapper.delPayment(new DelPaymentDto(11, -2));
        assertThat(paymentMapper.checkIuserAndIproduct(11, 1).getIstatus()).isEqualTo(-2);

        paymentMapper.delPayment(new DelPaymentDto(10, 1));
        assertThat(paymentMapper.checkIuserAndIproduct(10, 1).getIstatus()).isEqualTo(1);

        paymentMapper.delPayment(new DelPaymentDto(10, -2));
        assertThat(paymentMapper.checkIuserAndIproduct(10, 1).getIstatus()).isEqualTo(-2);

    }
}