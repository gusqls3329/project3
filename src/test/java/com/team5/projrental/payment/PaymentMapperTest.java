package com.team5.projrental.payment;

import com.team5.projrental.common.Flag;
import com.team5.projrental.payment.model.PaymentInsDto;
import com.team5.projrental.payment.model.proc.DelPaymentDto;
import com.team5.projrental.payment.model.proc.GetInfoForCheckIproductAndIuserResult;
import com.team5.projrental.payment.model.proc.GetPaymentListDto;
import com.team5.projrental.payment.model.proc.GetPaymentListResultDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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
        int ipayment = dto.getIpayment();

        assertThat(result1).isEqualTo(1);

        paymentMapper.insProductPayment(dto.getIproduct(), dto.getIpayment());

        // 조회한 상대가 나와야 함
        Integer iuser = 1;
        GetPaymentListResultDto getPaymentListResultDto = paymentMapper.getPaymentList(new GetPaymentListDto(iuser, Flag.ONE.getValue(), ipayment)).get(0);
        assertThat(dto.getIpayment()).isEqualTo(getPaymentListResultDto.getIpayment());
        assertThat(dto.getCode()).isEqualTo(getPaymentListResultDto.getCode());
        assertThat("감자현일").isEqualTo(getPaymentListResultDto.getNick());
        assertThat(0).isEqualTo(getPaymentListResultDto.getIstatus());
        assertThat(dto.getRentalDuration()).isEqualTo(getPaymentListResultDto.getRentalDuration());
        assertThat(dto.getRentalStartDate()).isEqualTo(getPaymentListResultDto.getRentalStartDate());
        assertThat(dto.getRentalEndDate()).isEqualTo(getPaymentListResultDto.getRentalEndDate());
        assertThat(dto.getIpayment()).isEqualTo(getPaymentListResultDto.getIpayment());
        assertThat(dto.getIproduct()).isEqualTo(getPaymentListResultDto.getIproduct());
        assertThat(dto.getPrice()).isEqualTo(getPaymentListResultDto.getPrice());


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

        GetInfoForCheckIproductAndIuserResult result1 = paymentMapper.checkIuserAndIproduct(11);
        assertThat(result1.getIstatus()).isNotEqualTo(-1);
        assertThat(result1.getIBuyer()).isEqualTo(4);
        assertThat(result1.getISeller()).isEqualTo(1);
        assertThat(result1.getRentalEndDate()).isEqualTo(LocalDate.of(2024, 1, 20));


    }

    @Test
    void delPayment() {

        paymentMapper.delPayment(new DelPaymentDto(11, -2));
        assertThat(paymentMapper.checkIuserAndIproduct(11).getIstatus()).isEqualTo(-2);

        paymentMapper.delPayment(new DelPaymentDto(10, 1));
        assertThat(paymentMapper.checkIuserAndIproduct(10).getIstatus()).isEqualTo(1);

        paymentMapper.delPayment(new DelPaymentDto(10, -2));
        assertThat(paymentMapper.checkIuserAndIproduct(10).getIstatus()).isEqualTo(-2);

    }

    @Test
    void getPaymentList() {

        List<GetPaymentListResultDto> paymentList = paymentMapper.getPaymentList(new GetPaymentListDto(1, 1, 0, true));
        assertThat(paymentList.size()).isEqualTo(1);
        assertThat(paymentList.get(0).getIuser()).isEqualTo(7);
        assertThat(paymentList.get(0).getNick()).isEqualTo("감자7");


        List<GetPaymentListResultDto> paymentList2 = paymentMapper.getPaymentList(new GetPaymentListDto(1, 2, 0, true));
        assertThat(paymentList2.size()).isEqualTo(6);
        assertThat(paymentList2.get(0).getIuser()).isEqualTo(3);
        assertThat(paymentList2.get(0).getNick()).isEqualTo("감자현일");
        assertThat(paymentList2.get(0).getIpayment()).isEqualTo(9);
        assertThat(paymentList2.get(0).getIproduct()).isEqualTo(10);
        assertThat(paymentList2.get(0).getIstatus()).isEqualTo(-4);
        assertThat(paymentList2.get(0).getRentalDuration()).isEqualTo(2);
        assertThat(paymentList2.get(0).getPrice()).isEqualTo(100000);
        assertThat(paymentList2.get(0).getDeposit()).isEqualTo(1200000);


        //

        int beforeRole1 = paymentMapper.getPaymentList(new GetPaymentListDto(2, 1, 0, true)).size();
        int beforeRole2 = paymentMapper.getPaymentList(new GetPaymentListDto(1, 2, 0, true)).size();
        for (int i = 1; i < 5; i++) {
            int buyer = 2;
            int iproduct = 11;
            LocalDate endDate = LocalDate.of(2023, 12, 10);
            PaymentInsDto dto = PaymentInsDto.builder()
                    .iproduct(iproduct)
                    .ibuyer(buyer)
                    .rentalDuration(3)
                    .price(10)
                    .code("test" + UUID.randomUUID().toString().substring(0, 10))
                    .ipaymentMethod(2)
                    .rentalStartDate(LocalDate.of(2023, 12, 1))
                    .rentalEndDate(endDate)
                    .build();
            paymentMapper.insPayment(dto);
            paymentMapper.insProductPayment(dto.getIproduct(), dto.getIpayment());

            assertThat(paymentMapper.getPaymentList(new GetPaymentListDto(2, 1, 0, true)).size()).isEqualTo(i + beforeRole1);
            assertThat(paymentMapper.getPaymentList(new GetPaymentListDto(1, 2, 0, true)).size()).isEqualTo(i + beforeRole2);

        }

    }
}