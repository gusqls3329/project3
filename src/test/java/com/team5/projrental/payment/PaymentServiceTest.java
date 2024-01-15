package com.team5.projrental.payment;

import com.team5.projrental.common.security.AuthenticationFacade;
import com.team5.projrental.common.utils.MyFileUtils;
import com.team5.projrental.payment.model.PaymentInsDto;
import com.team5.projrental.product.ProductRepository;
import com.team5.projrental.product.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@Import(PaymentService.class)
class PaymentServiceTest {


    @MockBean
    PaymentRepository paymentRepository;
    @MockBean
    ProductRepository productRepository;
    @MockBean
    AuthenticationFacade authenticationFacade;
    @MockBean
    MyFileUtils myFileUtils;


    @Autowired
    PaymentService paymentService;

    @Test
    void postPayment() {
        PaymentInsDto mockDate = PaymentInsDto.builder()
                .iproduct(1)
                .paymentMethod("kakao-pay")
                .rentalStartDate(LocalDate.of(2025, 1, 1))
                .rentalEndDate(LocalDate.of(2025, 3, 3))
                .depositPer(80).build();

        when(productRepository.findRentalPriceBy(any())).thenReturn(1000);



        paymentService.postPayment(mockDate);
    }

    @Test
    void delPayment() {
    }

    @Test
    void getAllPayment() {
    }

    @Test
    void getPayment() {
    }
}